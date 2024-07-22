package com.vice.bloodpressure.baseui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
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

   /* protected void setWebViewData(final WebView webView, final String url) {
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
    }*/

    protected void setWebViewData(final WebView webView, final String url) {
        // 初始化硬件加速
        initHardwareAccelerate();

        // 配置WebView设置
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // 启用JavaScript
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕大小
        webSettings.setUseWideViewPort(true); // 支持viewport标签，并使页面自适应屏幕宽度
        // 尝试使用NARROW_COLUMNS布局算法，这可能会帮助内容更好地适应屏幕宽度
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        String cachePath = this.getApplicationContext().getCacheDir().getPath();
        webSettings.setAppCachePath(cachePath);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        // 禁止缩放，让页面根据屏幕自适应，这一步可选，根据实际情况决定是否启用
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);

        // 加载URL
        webView.loadUrl(url);

        // 设置WebChromeClient以处理进度条等
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE); // 加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE); // 开始加载网页时显示进度条
                    progressBar.setProgress(newProgress); // 设置进度值
                }
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

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy(); // 销毁WebView释放资源
        }
    }
}
