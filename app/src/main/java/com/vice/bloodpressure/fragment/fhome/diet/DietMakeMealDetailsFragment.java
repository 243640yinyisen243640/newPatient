package com.vice.bloodpressure.fragment.fhome.diet;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietOneMealDetailsAdapter;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.MealIngMapInfo;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

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

    public static DietMakeMealDetailsFragment getInstance(String text) {

        DietMakeMealDetailsFragment detailsFragment = new DietMakeMealDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        containerView().addView(initView());
        initValues();
    }

    private void initValues() {
        List<MealIngMapInfo> resourcelist = new ArrayList<>();
        MealIngMapInfo mapInfo = new MealIngMapInfo();
        mapInfo.setName("猪肉");
        mapInfo.setIngK("60");
        resourcelist.add(mapInfo);
        MealIngMapInfo mapInfo1 = new MealIngMapInfo();
        mapInfo1.setName("猪肉");
        mapInfo1.setIngK("60");
        resourcelist.add(mapInfo1);
        DietOneMealDetailsAdapter resourceAdapter = new DietOneMealDetailsAdapter(getPageContext(), resourcelist);
        resourceLv.setAdapter(resourceAdapter);

        List<MealIngMapInfo> resourcelist1 = new ArrayList<>();
        MealIngMapInfo mapInfo2 = new MealIngMapInfo();
        mapInfo2.setName("猪肉");
        mapInfo2.setIngK("60");
        resourcelist1.add(mapInfo2);
        MealIngMapInfo mapInfo3 = new MealIngMapInfo();
        mapInfo3.setName("猪肉");
        mapInfo3.setIngK("60");
        resourcelist1.add(mapInfo3);
        DietOneMealDetailsAdapter seasoningAdapter = new DietOneMealDetailsAdapter(getPageContext(), resourcelist1);
        seasoningLv.setAdapter(seasoningAdapter);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_resource_make, null);
        resourceLv = view.findViewById(R.id.lv_diet_resource);
        seasoningLv = view.findViewById(R.id.lv_diet_seasoning);
        makeTextView = view.findViewById(R.id.tv_diet_make);
        return view;
    }

}
