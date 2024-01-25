package com.vice.bloodpressure.fragment.fhome;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServiceBmiAddActivity;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.view.X5WebView;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HomeBmiH5Fragment extends UIBaseFragment {
    private TextView addTv;
    private X5WebView x5WebView;
    private TextView numAndStateTv;
    private TextView timeTv;
    private UserInfo userInfo;

    public static HomeBmiH5Fragment getInstance(UserInfo userInfo) {

        HomeBmiH5Fragment bmiFragment = new HomeBmiH5Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bmiModule", userInfo);
        bmiFragment.setArguments(bundle);
        return bmiFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        userInfo = (UserInfo) getArguments().getSerializable("bmiModule");
        initView();
        setWebViewData(x5WebView, userInfo.getIframeUrl());
        setValues();
    }

    private void setValues() {
        //状态1偏低 2正常 3偏高
        if (!TextUtils.isEmpty(userInfo.getBgValue())) {

            numAndStateTv.setText(userInfo.getBmiValue());
            if ("1".equals(userInfo.getBmiStatus())) {
                numAndStateTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.home_bmi_low, 0);
            } else if ("2".equals(userInfo.getBmiStatus())) {
                numAndStateTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.home_bmi_high, 0);
            }
            timeTv.setText("测量时间：" + userInfo.getBmiDate());
        } else {
            numAndStateTv.setText("暂无数据");
            timeTv.setText("暂无数据");
        }
    }

    protected void setWebViewData(final X5WebView webView, final String url) {
        initHardwareAccelerate();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // H5页面加载不出图片
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setBlockNetworkImage(false);
        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //                if (newProgress == 100) {
                //                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                //
                //                } else {
                //                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                //                    progressBar.setProgress(newProgress);//设置进度值
                //                }
                super.onProgressChanged(webView, newProgress);
            }
        });
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            getActivity().getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        } catch (Exception e) {
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_bmi_h5, null);
        addTv = view.findViewById(R.id.tv_warning_bmi_add_h5);
        x5WebView = view.findViewById(R.id.web_warning_bmi_data);
        numAndStateTv = view.findViewById(R.id.tv_warning_bmi_num_state_h5);
        timeTv = view.findViewById(R.id.tv_warning_bmi_time_h5);
        addTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ServiceBmiAddActivity.class);
            startActivity(intent);
        });
        containerView().addView(view);
    }

}
