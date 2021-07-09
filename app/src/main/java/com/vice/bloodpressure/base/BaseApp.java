package com.vice.bloodpressure.base;

import android.app.Application;
import android.content.Context;

import com.vice.bloodpressure.baseimp.HHSoftApplicationInterface;
import com.vice.bloodpressure.basemanager.LoadViewManager;


public abstract class BaseApp extends Application {
    private HHSoftApplicationInterface applicationInfo;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppTopViewInfo();
        applicationInfo = new HHSoftApplicationInterface() {
            @Override
            public LoadViewManager.LoadMode appLoadMode() {
                return initAppLoadMode();
            }

            @Override
            public void appLoadViewInfo() {

            }

            @Override
            public boolean isNeedFullScreen() {
                return initIsFullScreen();
            }
        };
    }

    public HHSoftApplicationInterface applicationInfo() {
        return applicationInfo;
    }

    protected abstract void initAppTopViewInfo();

    protected abstract LoadViewManager.LoadMode initAppLoadMode();

    protected abstract boolean initIsFullScreen();

    public static Context getContext() {
        return context;
    }
}
