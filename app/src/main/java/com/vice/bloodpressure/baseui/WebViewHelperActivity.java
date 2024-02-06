package com.vice.bloodpressure.baseui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;

/**
 * 类描述：
 * 类传参： title (String) 标题
 * @author android.zsj
 * @date 2019/9/25
 */
public class WebViewHelperActivity extends UIBaseLoadActivity {

    /**
     * webView
     */
    private WebView webView;
    /**
     * 说明/协议链接
     */
    private String url;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText(getIntent().getStringExtra("title"));
        containerView().addView(initView());
        url = getIntent().getStringExtra("url");
        Log.i("yys","url===="+url);
        if (!TextUtils.isEmpty(url)) {
            setWebViewData(webView, url);
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        } else {
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        }
    }

    @Override
    protected void onPageLoad() {
        String explainId = getIntent().getStringExtra("explainId");
//        Call<String> requestCall = SettingDataManager.explainSettingUrl(explainId, (call, response) -> {
//            url = JsonParse.getParamInfo(response.result, "linkUrl");
//            if (!TextUtils.isEmpty(url)) {
//                setWebViewData(webView, url);
//                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
//            } else if (101 == response.code) {
//                loadViewManager().changeLoadState(LoadStatus.NODATA);
//            } else {
//                loadViewManager().changeLoadState(LoadStatus.FAILED);
//            }
//        }, (call, throwable) -> loadViewManager().changeLoadState(LoadStatus.FAILED));
//        addRequestCallToMap("explainSettingUrl", requestCall);
    }


    public View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_webview_help, null);
        progressBar = getViewByID(view, R.id.progressBar);
        webView = getViewByID(view, R.id.wv_helper);
        return view;
    }

    protected void setWebViewData(final WebView webView, final String url) {
        initHardwareAccelerate();

        webView.loadUrl(url);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
                super.onProgressChanged(webView, newProgress);
            }
        });
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        } catch (Exception e) {
        }
    }
}
