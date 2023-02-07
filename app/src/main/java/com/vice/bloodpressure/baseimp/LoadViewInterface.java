package com.vice.bloodpressure.baseimp;

import android.view.View;

import com.vice.bloodpressure.basemodel.LoadViewConfig;


public interface LoadViewInterface {
    void changeLoadState(LoadStatus status);

    void changeLoadStateWithHint(LoadStatus status, String hint);

    void setOnClickListener(LoadStatus status, View.OnClickListener listener);

    void init(LoadViewConfig config);
}
