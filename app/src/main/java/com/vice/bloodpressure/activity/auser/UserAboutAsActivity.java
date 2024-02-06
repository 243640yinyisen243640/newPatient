package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.base.XyApplication;
import com.vice.bloodpressure.basemanager.ConstantParamNew;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.baseui.WebViewHelperActivity;
import com.vice.bloodpressure.utils.AppUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.version.VersionUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:关于我们
 */
public class UserAboutAsActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView versionTextView;
    private TextView privacyTextView;
    private LinearLayout updataLinearLayout;
    private TextView userAgreementTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("关于我们");
        initView();
        initValues();
        initListener();
    }

    private void initValues() {
        versionTextView.setText("v" + AppUtils.appVersionName(XyApplication.getMyApplicationContext()));

    }

    private void initListener() {
        privacyTextView.setOnClickListener(this);
        userAgreementTextView.setOnClickListener(this);
        updataLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_about_us, null);
        versionTextView = view.findViewById(R.id.tv_user_about_version);
        privacyTextView = view.findViewById(R.id.tv_user_about_us_privacy);
        updataLinearLayout = view.findViewById(R.id.ll_user_about_us_updata);
        userAgreementTextView = view.findViewById(R.id.tv_user_about_us_user_agreement);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        String token = SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ACCESS_TOKEN);
        switch (v.getId()) {
            case R.id.tv_user_about_us_privacy:
                intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "用户服务协议");
                intent.putExtra("url", ConstantParamNew.IP + "pagesC/pages/userAgreement?" + "token=" + token + "&type=" + "1");
                startActivity(intent);
                break;
            case R.id.tv_user_about_us_user_agreement:
                intent = new Intent(getPageContext(), WebViewHelperActivity.class);
                intent.putExtra("title", "隐私政策");
                intent.putExtra("url", ConstantParamNew.IP + "pagesC/pages/userAgreement?" + "&type=" + "2");
                startActivity(intent);
                break;
            case R.id.ll_user_about_us_updata:
                VersionUtils.getInstance().updateNewVersion(getPageContext(), this, true);
                break;
            default:
                break;
        }
    }
}
