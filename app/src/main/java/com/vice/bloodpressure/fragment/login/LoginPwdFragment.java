package com.vice.bloodpressure.fragment.login;

import android.content.Intent;
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
import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.activity.login.ForgetPwdActivity;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:密码登录
 */
public class LoginPwdFragment extends UIBaseFragment implements View.OnClickListener {


    /**
     * 手机号码
     */
    private EditText phoneEditText;
    /**
     * 密码
     */
    private EditText passwordEditText;
    /**
     * 隐私政策
     */
    private TextView agreeTextView;
    /**
     * 密码是否可见
     */
    private CheckBox closeCheckBox;
    /**
     * 登录
     */
    private TextView sureTextView;
    /**
     * 忘记密码
     */
    private TextView forgetPwdTextView;

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        initValue();
        initListener();
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_pwd_login, null);
        phoneEditText = view.findViewById(R.id.et_login_pwd_phone);
        passwordEditText = view.findViewById(R.id.et_login_pwd_pwd);
        closeCheckBox = view.findViewById(R.id.cb_login_pwd_close);
        agreeTextView = view.findViewById(R.id.tv_login_pwd_agreement);
        sureTextView = view.findViewById(R.id.tv_login_pwd_sure);
        forgetPwdTextView = view.findViewById(R.id.tv_login_pwd_forget);
        return view;
    }

    private void initValue() {
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
        //显示密码不可见
        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
    }

    private void initListener() {
        sureTextView.setOnClickListener(this);
        forgetPwdTextView.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tv_login_pwd_sure:
                startActivity(new Intent(getPageContext(), MainActivity.class));
                //                login();
                break;
            case R.id.tv_login_pwd_agreement:
                agreeTextView.setSelected(!agreeTextView.isSelected());
                break;
            case R.id.tv_login_pwd_forget:
                //忘记密码
                intent = new Intent(getPageContext(), ForgetPwdActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String deviceToken = UserInfoUtils.getClientID(getPageContext());
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号/身份证号");
            return;
        }

        String pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入密码");
            return;
        }

        ToastUtils.getInstance().showProgressDialog(getPageContext(), R.string.waiting, false);

    }


}
