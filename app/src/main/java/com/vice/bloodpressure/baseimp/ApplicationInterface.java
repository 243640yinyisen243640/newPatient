package com.vice.bloodpressure.baseimp;


import com.vice.bloodpressure.basemanager.LoadViewManager;

public interface ApplicationInterface {
    LoadViewManager.LoadMode appLoadMode();

    void appLoadViewInfo();

    boolean isNeedFullScreen();
}
