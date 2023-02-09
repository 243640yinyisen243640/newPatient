package com.vice.bloodpressure.fragment.fhome.diet;

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
public class DietMakeMealDetailsFragment extends UIBaseLoadFragment {

    public static DietMakeMealDetailsFragment getInstance(String text) {

        DietMakeMealDetailsFragment detailsFragment = new DietMakeMealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    protected void onCreate() {

        containerView().addView(initView());

    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_resource_make, null);

        return view;
    }

    @Override
    protected void onPageLoad() {

    }
}
