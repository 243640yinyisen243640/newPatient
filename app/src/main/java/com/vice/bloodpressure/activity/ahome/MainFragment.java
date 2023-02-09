package com.vice.bloodpressure.activity.ahome;

import android.os.Bundle;

import com.vice.bloodpressure.baseui.UIBaseLoadFragment;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MainFragment extends UIBaseLoadFragment {
    public static MainFragment getInstance(){

        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("","");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }
    @Override
    protected void onCreate() {

    }

    @Override
    protected void onPageLoad() {

    }
}
