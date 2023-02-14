package com.vice.bloodpressure.fragment;

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
public class textFragment extends UIBaseLoadFragment {
    public static textFragment getInstance() {

        textFragment mainFragment = new textFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        View view = View.inflate(getPageContext(), R.layout.text, null);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
