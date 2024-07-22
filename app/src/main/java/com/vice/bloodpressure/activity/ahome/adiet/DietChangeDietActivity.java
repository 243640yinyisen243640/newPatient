package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.DietMealOneMealDetailsAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.List;


/**
 * 类名：更改饮食方案
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietChangeDietActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView breakfastTv;
    /**
     * 午餐
     */
    private TextView lunchTv;
    /**
     * 晚餐
     */
    private TextView dinnerTv;
    /**
     * 早餐列表
     */
    private NoScrollListView breakfastNlv;
    /**
     * 午餐列表
     */
    private NoScrollListView lunchNlv;
    /**
     * 晚餐列表
     */
    private NoScrollListView dinnerNlv;

    private List<MealExclusiveInfo> breakLsit;
    private List<MealExclusiveInfo> lunchLsit;
    private List<MealExclusiveInfo> dinnerLsit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breakLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("breaklist");
        lunchLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("lunchlist");
        dinnerLsit = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("dinnerlist");
        initView();
        topViewManager().titleTextView().setText(R.string.diet_activity_change_diet_top_title);
        initValues();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_change_diet_plan, null);
        containerView().addView(view);
        breakfastTv = view.findViewById(R.id.tv_diet_change_breakfast);
        lunchTv = view.findViewById(R.id.tv_diet_change_lunch);
        dinnerTv = view.findViewById(R.id.tv_diet_change_dinner);
        breakfastNlv = view.findViewById(R.id.nlv_diet_change_breakfast);
        lunchNlv = view.findViewById(R.id.nlv_diet_change_lunch);
        dinnerNlv = view.findViewById(R.id.nlv_diet_change_dinner);
    }

    private void initValues() {

        DietMealOneMealDetailsAdapter breakAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), breakLsit,"3",false,null);
        breakfastNlv.setAdapter(breakAdapter);
        breakfastNlv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
            intent.putExtra("meal", "早餐");
            startActivity(intent);
        });

        DietMealOneMealDetailsAdapter lunchAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), lunchLsit,"3",false,null);
        lunchNlv.setAdapter(lunchAdapter);
        lunchNlv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
            intent.putExtra("meal", "午餐");
            startActivity(intent);
        });

        DietMealOneMealDetailsAdapter dinnerAdapter = new DietMealOneMealDetailsAdapter(getPageContext(), dinnerLsit,"3",false,null);
        dinnerNlv.setAdapter(dinnerAdapter);
        dinnerNlv.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
            intent.putExtra("meal", "晚餐");
            startActivity(intent);
        });
    }
}
