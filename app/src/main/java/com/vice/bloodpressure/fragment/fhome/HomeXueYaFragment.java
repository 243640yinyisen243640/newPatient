package com.vice.bloodpressure.fragment.fhome;

import android.os.Bundle;
import android.view.View;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HomeXueYaFragment extends UIBaseFragment {

    public static HomeXueYaFragment getInstance(String text) {

        HomeXueYaFragment xueYaFragment = new HomeXueYaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        xueYaFragment.setArguments(bundle);
        return xueYaFragment;
    }
    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_home_xuetang, null);
        containerView().addView(view);
    }
}
