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
public class HomeBmiFragment extends UIBaseFragment {
    public static HomeBmiFragment getInstance(String text) {

        HomeBmiFragment bmiFragment = new HomeBmiFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        bmiFragment.setArguments(bundle);
        return bmiFragment;
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
