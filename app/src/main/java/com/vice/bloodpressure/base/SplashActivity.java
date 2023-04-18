package com.vice.bloodpressure.base;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lzx.starrysky.StarrySky;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.baseui.WebViewHelperActivity;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.utils.ScreenUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;

/**
 * 描述: 启动页
 * 作者: LYD
 * 创建日期: 2019/3/25 15:06
 */
public class SplashActivity extends UIBaseActivity {
    private long countDownTime = 3000;//单位毫秒
    /**
     * 倒计时
     */
    private CountDownTimer timer;

    /**
     * 隐私政策弹出框
     */
    private Dialog protectDialog;
    private String spanColor = "#FFC600";//隐私政策span颜色值


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().topView().removeAllViews();
        getWindow().setBackgroundDrawable(null);
        initView();
        setSplash();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_splash, null);
        containerView().addView(view);
    }

    /**
     * 设置启动页
     */
    private void setSplash() {
        if ("1".equals(SharedPreferencesUtils.getInfo(this, SharedPreferencesConstant.IS_AGREE))) {
            timer = new CountDownTimer(countDownTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    judgeIsTokenEmpty();
                }
            };
            timer.start();
        } else {
            showPrivacyProtectDialog();
        }
    }

    /**
     * 隐私权限提示
     */
    private void showPrivacyProtectDialog() {
        if (protectDialog == null) {
            protectDialog = new Dialog(getPageContext(), R.style.HuaHanSoft_Dialog_Base);
            View view = View.inflate(getPageContext(), R.layout.dialog_privacy_protect, null);
            protectDialog.setContentView(view);
            WindowManager.LayoutParams attributes = protectDialog.getWindow().getAttributes();
            attributes.width = 4 * ScreenUtils.screenWidth(getPageContext()) / 5;
            attributes.height = 4 * ScreenUtils.screenHeight(getPageContext()) / 5;
            protectDialog.getWindow().setAttributes(attributes);
            protectDialog.setCancelable(false);

            TextView serviceAgreementTextView = view.findViewById(R.id.tv_dpp_service_agreement);
            TextView disagreeTextView = view.findViewById(R.id.tv_dpp_disagree);
            TextView agressTextView = view.findViewById(R.id.tv_dpp_agree);

            String privacyProtectHint = getString(R.string.privacy_protect_hint1);
            SpannableString ss = new SpannableString(privacyProtectHint);

            ss.setSpan(new UnderLineClickSpan() {
                @Override
                public void onClick(View widget) {
                    jumpToUserPrivacy();
                }
            }, privacyProtectHint.indexOf("《"), privacyProtectHint.indexOf("》") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.parseColor(spanColor)), privacyProtectHint.indexOf("《"), privacyProtectHint.indexOf("》") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            ss.setSpan(new UnderLineClickSpan() {
                @Override
                public void onClick(View widget) {
                    jumpToUserAgreement();
                }
            }, privacyProtectHint.lastIndexOf("《"), privacyProtectHint.lastIndexOf("》") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(Color.parseColor(spanColor)), privacyProtectHint.lastIndexOf("《"), privacyProtectHint.lastIndexOf("》") + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            serviceAgreementTextView.setText(ss);
            serviceAgreementTextView.setHighlightColor(Color.TRANSPARENT);
            serviceAgreementTextView.setMovementMethod(LinkMovementMethod.getInstance());

            disagreeTextView.setOnClickListener(v -> {
                protectDialog.dismiss();
                finish();
            });
            agressTextView.setOnClickListener(v -> {
                SharedPreferencesUtils.saveInfo(getPageContext(), SharedPreferencesConstant.IS_AGREE, "1");
                initAudio();
                protectDialog.dismiss();
                judgeIsTokenEmpty();
            });
            protectDialog.show();
        }
    }


    private void judgeIsTokenEmpty() {
        if (TextUtils.isEmpty(UserInfoUtils.getAcceToken(getPageContext()))) {
            Intent intent = new Intent(getPageContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Call<String> requestCall = LoginDataManager.checkToken(UserInfoUtils.getAcceToken(getPageContext()), (call, response) -> {
                if ("0000".equals(response.code) && response.data) {
                    Intent mainIntent = new Intent(getPageContext(), MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Intent intent = new Intent(getPageContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, (call, t) -> {
                Intent intent = new Intent(getPageContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            });
            addRequestCallToMap("checkToken", requestCall);
        }

    }

    private abstract class UnderLineClickSpan extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }
    }

    private void initAudio() {
        StarrySky.init(getApplication()).apply();
    }

    /**
     * 页面跳转-用户政策
     */
    private void jumpToUserPrivacy() {
        Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
        intent.putExtra("title", "用户服务协议");
        intent.putExtra("url", "file:///android_asset/user_protocol.html");
        startActivity(intent);
    }

    /**
     * 页面跳转-用户政策
     */
    private void jumpToUserAgreement() {
        Intent intent = new Intent(getPageContext(), WebViewHelperActivity.class);
        intent.putExtra("title", "隐私政策");
        intent.putExtra("url", "http://chronics.xiyuns.cn/index/caseapp");
        startActivity(intent);
    }

}
