package com.vice.bloodpressure.base;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.multidex.MultiDex;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DefaultTopViewManager;
import com.vice.bloodpressure.basemanager.LoadViewManager;


public class XyApplication extends BaseApplication {
    private static final String TAG = XyApplication.class.getSimpleName();
    private static XyApplication application;

        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(this); //添加这一行
        }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        /**
         * 第三方初始化
         */
        //        Map<String, String> appIDMap = new HashMap<>();
        //        appIDMap.put(HHSoftThirdTools.KEY_WECHAT_APP_ID, ThirdLoginConstants.WX_APP_ID);
        //        appIDMap.put(HHSoftThirdTools.KEY_QQ_APP_ID, "1106246987");
        //        appIDMap.put(HHSoftThirdTools.KEY_SINA_APP_ID, "3050346258");
        //        HHSoftThirdTools.init(getApplicationContext(), appIDMap);
        //
        ////        RongIM.init(getMyApplicationContext(), "8luwapkv84iyl");
        //        RongUtils.getInstance().rongCloudInit(getMyApplicationContext());
    }

    @Override
    protected void initAppTopViewInfo() {
                DefaultTopViewManager.mTopViewInfo.titleSize = 20;
                DefaultTopViewManager.mTopViewInfo.titleTextColor = "#323232";
                DefaultTopViewManager.mTopViewInfo.topLineColor = "#F7F7F7";
                DefaultTopViewManager.mTopViewInfo.topLineHeight = 0;
                DefaultTopViewManager.mTopViewInfo.backLeftDrawable = R.drawable.top_back_black;
                DefaultTopViewManager.mTopViewInfo.topBackgroundDrawableRes = 0;
                DefaultTopViewManager.mTopViewInfo.topBackgroundColor = "#FFFFFF";
                LoadViewManager.mLoadViewConfig.loadViewBgColor = "#FFFFFF";

        // 为第三方自定义推送服务
        //        PushManager.getInstance().initialize(getApplicationContext(), GetuiPushService.class);
        //        PushManager.getInstance().registerPushIntentService(getApplicationContext(), GetuiIntentService.class);

        //        SDKInitializer.initiali
        //        ze(getApplicationContext());
        //        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        //        SDKInitializer.setCoordType(CoordType.BD09LL);
        initX5WebView();
    }

    @Override
    protected LoadViewManager.LoadMode initAppLoadMode() {
        return LoadViewManager.LoadMode.DRAWABLE;
    }

    @Override
    protected boolean initIsFullScreen() {
        return true;
    }

    public static Context getMyApplicationContext() {
        return application.getApplicationContext();
    }

    /**
     * 初始化X5WebView
     */
    private void initX5WebView() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        //        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        //            @Override
        //            public void onViewInitFinished(boolean arg0) {
        //                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
        //                HHSoftLogUtils.i("app", " onViewInitFinished is " + arg0);
        //            }
        //
        //            @Override
        //
        //            public void onCoreInitFinished() {
        //            }
        //        };
        //        //x5内核初始化接口
        //        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            //非默认值
            getResources();
        }

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res != null && res.getConfiguration().fontScale != 1.0f) {
            //非默认值
            Configuration newConfig = res.getConfiguration();
            newConfig.fontScale = 1.0f;
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }
}

