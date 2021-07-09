package com.vice.bloodpressure.baseimp;

import android.view.View;

import com.vice.bloodpressure.basemodel.LoadViewConfig;


public interface HHSoftLoadViewInterface {
    void changeLoadState(HHSoftLoadStatus status);

    void changeLoadStateWithHint(HHSoftLoadStatus status, String hint);

    void setOnClickListener(HHSoftLoadStatus status, View.OnClickListener listener);

    void init(LoadViewConfig config);
}
