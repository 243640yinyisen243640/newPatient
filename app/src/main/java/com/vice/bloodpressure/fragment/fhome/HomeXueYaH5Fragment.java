package com.vice.bloodpressure.fragment.fhome;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.aservice.ServicePressureAddActivity;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.view.X5WebView;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HomeXueYaH5Fragment extends UIBaseFragment {
    private TextView addTv;
    private X5WebView x5WebView;
    private TextView numTv;
    private UserInfo userInfo;

    public static HomeXueYaH5Fragment getInstance(UserInfo userInfo) {

        HomeXueYaH5Fragment xueYaFragment = new HomeXueYaH5Fragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bpModule", userInfo);
        xueYaFragment.setArguments(bundle);
        return xueYaFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        userInfo = (UserInfo) getArguments().getSerializable("bpModule");
        initView();
        initValues();
        setWebViewData(x5WebView, userInfo.getIframeUrl());
    }


    private void initValues() {
        if (!TextUtils.isEmpty(userInfo.getTakeSum())) {
            SpannableStringBuilder stringBuilder = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "共测量", userInfo.getTakeSum(), "次;");
            SpannableStringBuilder stringBuilder1 = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "有", userInfo.getTakeHeightNum(), "偏高;");
            SpannableStringBuilder stringBuilder2 = setTextType(Color.parseColor("#2A2A2A"), Color.parseColor("#00C27F"), "", userInfo.getTakeNormalNum(), "正常;");
            numTv.setText(stringBuilder.append(stringBuilder1).append(stringBuilder2));
        } else {
            numTv.setText("暂无数据");
        }

    }

    private SpannableStringBuilder setTextType(int startColor, int endColor, String text1, String text2, String text3) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(text1);
        int length1 = builder.length();
        builder.append(text2);
        int length2 = builder.length();
        builder.append(text3);
        builder.setSpan(new ForegroundColorSpan(startColor), 0, length1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSpan(new ForegroundColorSpan(endColor), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new StyleSpan(Typeface.BOLD), length1, length2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
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
        View view = View.inflate(getPageContext(), R.layout.fragment_home_xueya_h5, null);
        addTv = view.findViewById(R.id.tv_home_fm_xy_add_h5);
        x5WebView = view.findViewById(R.id.web_home_fm_xy_data);
        numTv = view.findViewById(R.id.tv_home_fm_xy_num_h5);
        addTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ServicePressureAddActivity.class);
            startActivity(intent);
        });
        containerView().addView(view);
    }


}
