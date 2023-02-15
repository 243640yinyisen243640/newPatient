package com.vice.bloodpressure.fragment.fhome;

import android.os.Bundle;
import android.view.View;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MainHomeFragment extends UIBaseLoadFragment {



    public static MainHomeFragment getInstance() {

        MainHomeFragment mainFragment = new MainHomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }
    @Override
    protected void onCreate() {

        topViewManager().topView().removeAllViews();
        initTopView();
        initView();
    }

    private void initView() {

        View view = View.inflate(getPageContext(), R.layout.fragment_main_home_first, null);
        containerView().addView(view);
    }

    private void initTopView() {
        View topView = View.inflate(getPageContext(), R.layout.include_main_home_topview, null);
        topViewManager().topView().addView(topView);
    }

    @Override
    protected void onPageLoad() {

    }
}
