package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietProgrammeMealAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.MealInfo;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：自定义饮食方案
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietProgrammeChooseActivity extends UIBaseActivity implements View.OnClickListener {

    private TextView breakfastTv;
    private NoScrollListView breakfastRv;
    private TextView lunchTv;
    private NoScrollListView lunchRv;
    private TextView dinnerTv;
    private NoScrollListView dinnerRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText(R.string.diet_activity_custom_diet_top_title);
        initView();
        initListener();

        initValues();

    }

    private void initValues() {

        List<MealInfo> breakList = new ArrayList<>();
        breakList.add(new MealInfo("100"));
        breakList.add(new MealInfo("100"));
        breakList.add(new MealInfo("100"));
        DietProgrammeMealAdapter breakListAdapter = new DietProgrammeMealAdapter(getPageContext(), breakList);
        breakfastRv.setAdapter(breakListAdapter);

        List<MealInfo> LunchkList = new ArrayList<>();
        LunchkList.add(new MealInfo("100"));
        LunchkList.add(new MealInfo("100"));
        LunchkList.add(new MealInfo("100"));
        DietProgrammeMealAdapter lunchListAdapter = new DietProgrammeMealAdapter(getPageContext(), LunchkList);
        lunchRv.setAdapter(lunchListAdapter);


        List<MealInfo> dinnerList = new ArrayList<>();
        dinnerList.add(new MealInfo("100"));
        dinnerList.add(new MealInfo("100"));
        dinnerList.add(new MealInfo("100"));
        DietProgrammeMealAdapter dinnerListAdapter = new DietProgrammeMealAdapter(getPageContext(), dinnerList);
        dinnerRv.setAdapter(dinnerListAdapter);
    }

    private void initListener() {
        breakfastTv.setOnClickListener(this);
        lunchTv.setOnClickListener(this);
        dinnerTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_programme_choose, null);
        breakfastTv = view.findViewById(R.id.tv_diet_custom_plan_breakfast);
        breakfastRv = view.findViewById(R.id.rv_diet_custom_plan_breakfast);
        lunchTv = view.findViewById(R.id.tv_diet_custom_plan_lunch);
        lunchRv = view.findViewById(R.id.rv_diet_custom_plan_lunch);
        dinnerTv = view.findViewById(R.id.tv_diet_custom_plan_dinner);
        dinnerRv = view.findViewById(R.id.rv_diet_custom_plan_dinner);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_diet_custom_plan_breakfast:
                Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
                intent.putExtra("meal", "早餐");
                startActivity(intent);
                break;
            case R.id.tv_diet_custom_plan_lunch:
                break;
            case R.id.tv_diet_custom_plan_dinner:
                break;
            default:
                break;
        }
    }
}
