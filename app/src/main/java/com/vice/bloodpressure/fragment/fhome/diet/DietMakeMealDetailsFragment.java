package com.vice.bloodpressure.fragment.fhome.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietOneMealDetailsAdapter;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.view.NoScrollListView;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietMakeMealDetailsFragment extends UIBaseFragment {
    private NoScrollListView resourceLv;
    private NoScrollListView seasoningLv;
    private TextView makeTextView;

    private MealExclusiveInfo mealExclusiveInfo;

    public static DietMakeMealDetailsFragment getInstance(MealExclusiveInfo mealExclusiveInfo) {

        DietMakeMealDetailsFragment detailsFragment = new DietMakeMealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mealExclusiveInfo", mealExclusiveInfo);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        mealExclusiveInfo= (MealExclusiveInfo) getArguments().getSerializable("mealExclusiveInfo");
        initValues();
    }

    private void initValues() {

        DietOneMealDetailsAdapter resourceAdapter = new DietOneMealDetailsAdapter(getPageContext(), mealExclusiveInfo.getIngMap(),"1");
        resourceLv.setAdapter(resourceAdapter);


        DietOneMealDetailsAdapter seasoningAdapter = new DietOneMealDetailsAdapter(getPageContext(), mealExclusiveInfo.getSeasoningList(),"2");
        seasoningLv.setAdapter(seasoningAdapter);
        makeTextView.setText(mealExclusiveInfo.getPractice());
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_resource_make, null);
        resourceLv = view.findViewById(R.id.lv_diet_resource);
        seasoningLv = view.findViewById(R.id.lv_diet_seasoning);
        makeTextView = view.findViewById(R.id.tv_diet_make);
        return view;
    }

}
