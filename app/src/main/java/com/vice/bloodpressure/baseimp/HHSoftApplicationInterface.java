package com.vice.bloodpressure.baseimp;


import com.vice.bloodpressure.basemanager.LoadViewManager;

public interface HHSoftApplicationInterface {
    LoadViewManager.LoadMode appLoadMode();

    void appLoadViewInfo();

    boolean isNeedFullScreen();
}
