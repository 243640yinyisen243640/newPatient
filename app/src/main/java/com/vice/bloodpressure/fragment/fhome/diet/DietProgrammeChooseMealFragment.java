package com.vice.bloodpressure.fragment.fhome.diet;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietChangeMealListAdapter;
import com.vice.bloodpressure.baseui.UIBaseFragment;
import com.vice.bloodpressure.model.MealInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DietProgrammeChooseMealFragment extends UIBaseFragment {
    private RecyclerView recyclerView;

    public static DietProgrammeChooseMealFragment getInstance() {

        DietProgrammeChooseMealFragment mainFragment = new DietProgrammeChooseMealFragment();
        Bundle bundle = new Bundle();
        bundle.putString("", "");
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    protected void onCreate() {
        topViewManager().topView().removeAllViews();
        initView();
        initValues();
    }

    private void initValues() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getPageContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<MealInfo> list = new ArrayList<>();
        list.add(new MealInfo("100"));
        list.add(new MealInfo("100"));
        list.add(new MealInfo("100"));
        DietChangeMealListAdapter mealListAdapter = new DietChangeMealListAdapter(getPageContext(), list);
        recyclerView.setAdapter(mealListAdapter);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.fragment_diet_programme_choose, null);
        recyclerView = view.findViewById(R.id.rv_diet_programme_choose_data);
        containerView().addView(view);
    }
}
