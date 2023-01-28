package com.vice.bloodpressure.fragment;


import com.vice.bloodpressure.baseui.UIBaseFragment;

public class MainFragment extends UIBaseFragment {
    public static MainFragment getInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
    }
}
