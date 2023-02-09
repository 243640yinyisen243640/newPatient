package com.vice.bloodpressure.fragment;


import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadRefreshFragment;
import com.vice.bloodpressure.utils.StatusBarUtils;

public class MainFragment extends UIBaseLoadRefreshFragment {
    public static MainFragment getInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        topViewManager().topView().removeAllViews();
        StatusBarUtils.statusBarColor(getActivity(), ContextCompat.getColor(getPageContext(), R.color.main_base_color));
        refreshLayout().setOnRefreshListener(refreshLayout -> onPageLoad());
    }


    @Override
    protected void onPageLoad() {

    }
}
