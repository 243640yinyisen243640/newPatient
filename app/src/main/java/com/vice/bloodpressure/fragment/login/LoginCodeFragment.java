package com.vice.bloodpressure.fragment.login;

import android.content.Intent;
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
import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:验证码登录
 */
public class LoginCodeFragment extends UIBaseFragment implements View.OnClickListener {
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
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        initListener();
    }

    private void sureToLogin() {
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

        if (!agreeTextView.isSelected()) {
            ToastUtils.getInstance().showToast(getPageContext(), "请先同意隐私政策和用户协议");
            return;
        }
        Call<String> requestCall = LoginDataManager.userLoginForCode(phone, verification,UserInfoUtils.getAcceToken(getPageContext()), (call, response) -> {
            ToastUtils.getInstance().dismissProgressDialog();
            if ("0000".equals(response.code)) {
                UserInfo userInfo = (UserInfo) response.object;
                UserInfoUtils.saveLoginInfo(getPageContext(), userInfo);
                Intent intent = new Intent(getPageContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userLoginForCode", requestCall);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_code_sure:
                sureToLogin();
                break;
            case R.id.tv_login_code_get:
                getVerifiCode();
                break;
            case R.id.tv_login_code_agreement:
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
        View view = View.inflate(getPageContext(), R.layout.fragment_code_login, null);
        phoneEditText = view.findViewById(R.id.tv_login_code_phone);
        verificationEditText = view.findViewById(R.id.et_login_code_code);
        getVerTextView = view.findViewById(R.id.tv_login_code_get);
        sureTextView = view.findViewById(R.id.tv_login_code_sure);
        agreeTextView = view.findViewById(R.id.tv_login_code_agreement);

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
