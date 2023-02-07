package com.vice.bloodpressure.base;

import android.app.Application;
import android.content.Context;

import com.vice.bloodpressure.baseimp.ApplicationInterface;
import com.vice.bloodpressure.basemanager.LoadViewManager;


public abstract class BaseApplication extends Application {
    private ApplicationInterface applicationInfo;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppTopViewInfo();
        applicationInfo = new ApplicationInterface() {
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

    public ApplicationInterface applicationInfo() {
        return applicationInfo;
    }

    protected abstract void initAppTopViewInfo();

    protected abstract LoadViewManager.LoadMode initAppLoadMode();

    protected abstract boolean initIsFullScreen();

    public static Context getContext() {
        return context;
    }
}
