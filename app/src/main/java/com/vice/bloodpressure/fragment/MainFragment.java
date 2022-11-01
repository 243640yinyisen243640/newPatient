package com.vice.bloodpressure.fragment;


import com.vice.bloodpressure.baseui.UIBaseFragment;

public class MainFragment extends UIBaseFragment {

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
    }
}
