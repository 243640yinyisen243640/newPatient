package com.vice.bloodpressure.activity.login;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.TurnUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

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
    }

    private void sureToRegister() {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号码");
            return;
        }
        if (!TurnUtils.isMobileNO(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入合法的手机号");
            return;
        }

        String verification = verificationEditText.getText().toString().trim();
        if (TextUtils.isEmpty(verification)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入验证码");
            return;
        }

        if (!agreeTextView.isSelected()) {
            ToastUtils.getInstance().showToast(getPageContext(), "请先同意隐私政策和用户协议");
            return;
        }
        String deviceToken = UserInfoUtils.getClientID(getPageContext());
        ToastUtils.getInstance().showProgressDialog(getPageContext(), R.string.waiting);
        //        Call<String> requestCall = LoginDataManager.userRegister(phone, deviceToken, verification, pwd, "0", "0", "0", (call, response) -> {
        //            ToastUtils.getInstance().dismissProgressDialog();
        //            if (response.code == 100) {
        //                UserInfo userInfo = (UserInfo) response.object;
        //                //                UserInfoUtils.saveLoginInfo(getPageContext(), userInfo);
        //                Intent intent = new Intent(getPageContext(), UserFirstLoginActivity.class);
        //                intent.putExtra("userInfo", userInfo);
        //                startActivity(intent);
        //                finish();
        //            } else {
        //                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
        //            }
        //        }, (call, t) -> {
        //            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        //        });
        //        addRequestCallToMap("userRegister", requestCall);
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
        if (!TurnUtils.isMobileNO(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入合法的手机号");
            return;
        }
        //        ToastUtils.getInstance().showProgressDialog(getPageContext(), R.string.waiting, false);
        //        Call<String> requestCall = LoginDataManager.verifyCodeByTel(phone, "1", (call, response) -> {
        //            ToastUtils.getInstance().dismissProgressDialog();
        //            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
        //            if (100 == response.code) {
        //                CountDownTask.getInstence().showTimer(getVerTextView, 120, getPageContext());
        //            }
        //        }, (call, throwable) -> {
        //            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        //        });
        //        addRequestCallToMap("verifyCodeByTel", requestCall);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_register, null);
        phoneEditText = view.findViewById(R.id.et_register_phone);
        verificationEditText = view.findViewById(R.id.et_register_code);
        getVerTextView = view.findViewById(R.id.tv_register_get);
        sureTextView = view.findViewById(R.id.tv_register_sure);
        agreeTextView = view.findViewById(R.id.tv_register_agreement);
        agreeTextView.setSelected(true);

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
                //                Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                //                intent.putExtra("title", getString(R.string.privacy_appointment));
                //                intent.putExtra("explainId", "13");
                //                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, startUser, endUser, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        stringBuilder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                //                Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                //                intent.putExtra("title", getString(R.string.privacy_policy));
                //                intent.putExtra("explainId", "12");
                //                startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, startPrivacy, endPrivacy, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        agreeTextView.setMovementMethod(LinkMovementMethod.getInstance());
        agreeTextView.setText(stringBuilder);
        return view;
    }

    private void initListener() {
        getVerTextView.setOnClickListener(this);
        sureTextView.setOnClickListener(this);
        agreeTextView.setOnClickListener(this);
    }
}
