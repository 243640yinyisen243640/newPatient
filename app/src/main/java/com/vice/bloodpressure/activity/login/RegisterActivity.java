package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.baseui.WebViewHelperActivity;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.countdown.CountDownTask;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:注册
 */
public class RegisterActivity extends UIBaseActivity implements View.OnClickListener {
    /**
     * 手机号
     */
    private EditText phoneEditText;
    /**
     * 验证码
     */
    private EditText verificationEditText;
    /**
     * 密码
     */
    private EditText passwordEditText;

    /**
     * 密码是否可见
     */
    private CheckBox closeCheckBox;
    /**
     * 获取验证码
     */
    private TextView getVerTextView;

    /**
     * 同意协议
     */
    private TextView agreeTextView;

    private TextView sureTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("注册");
        containerView().addView(initView());
        initListener();
        initValues();
    }

    private void initValues() {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        stringBuilder.append(getString(R.string.login_agreement_left));
        int startUser = stringBuilder.length();
        stringBuilder.append(getString(R.string.login_agreement_user_agreement));
        int endUser = stringBuilder.length();
        stringBuilder.append(getString(R.string.login_agreement_user_and));
        int startPrivacy = stringBuilder.length();
        stringBuilder.append(getString(R.string.login_agreement_privacy));
        int endPrivacy = stringBuilder.length();
        stringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getPageContext(), R.color.blue_1896)), startUser, endUser, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getPageContext(), R.color.blue_1896)), startPrivacy, endPrivacy, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        stringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "用户服务协议");
                intent.putExtra("url", ConstantParamNew.IP + "pagesC/pages/userAgreement?" + "type=" + "1");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, startUser, endUser, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        stringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("url", ConstantParamNew.IP + "pagesC/pages/userAgreement?" + "type=" + "2");
                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, startPrivacy, endPrivacy, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        agreeTextView.setMovementMethod(LinkMovementMethod.getInstance());
        agreeTextView.setText(stringBuilder);
        //显示密码不可见
        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
    }


    private void sureToRegister() {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号码");
            return;
        }


        String verification = verificationEditText.getText().toString().trim();
        if (TextUtils.isEmpty(verification)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入验证码");
            return;
        }
        String pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入密码");
            return;
        }

        if (!agreeTextView.isSelected()) {
            ToastUtils.getInstance().showToast(getPageContext(), "请先同意隐私政策和用户协议");
            return;
        }

        Call<String> requestCall = LoginDataManager.userRegister(phone, pwd, verification, (call, response) -> {
            if ("0000".equals(response.code)) {
                UserInfo userInfo = (UserInfo) response.object;
                UserInfoUtils.saveLoginInfo(getPageContext(), userInfo);
                Intent intent = new Intent(getPageContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {

            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userRegister", requestCall);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_sure:
                sureToRegister();
                break;
            case R.id.tv_register_get:
                getVerifiCode();
                break;
            case R.id.tv_register_agreement:
                agreeTextView.setSelected(!agreeTextView.isSelected());
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifiCode() {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号码");
            return;
        }

        Call<String> requestCall = LoginDataManager.verifyCodeByTel(phone, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                CountDownTask.getInstence().showTimer(getVerTextView, 60, getPageContext());
            }
        }, (call, throwable) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("verifyCodeByTel", requestCall);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_register, null);
        phoneEditText = view.findViewById(R.id.et_register_phone);
        verificationEditText = view.findViewById(R.id.et_register_code);
        passwordEditText = view.findViewById(R.id.et_register_pwd);
        closeCheckBox = view.findViewById(R.id.cb_register_close);
        getVerTextView = view.findViewById(R.id.tv_register_get);
        sureTextView = view.findViewById(R.id.tv_register_sure);
        agreeTextView = view.findViewById(R.id.tv_register_agreement);
        return view;
    }

    private void initListener() {
        getVerTextView.setOnClickListener(this);
        sureTextView.setOnClickListener(this);
        agreeTextView.setOnClickListener(this);
        closeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //显示密码不可见
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
            } else {
                //显示明文可见
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
            }
        });
    }
}
