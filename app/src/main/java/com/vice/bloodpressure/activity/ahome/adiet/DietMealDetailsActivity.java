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
 * 类名：一餐详情展示
 * 传参：meal
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietMealDetailsActivity extends UIBaseActivity {
    /**
     * 早餐
     */
    private TextView mealTitleTv;

    /**
     * 早餐列表
     */
    private NoScrollListView mealTitleRv;

    private String titleMeal;

    private List<MealExclusiveInfo> mealExclusiveInfoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleMeal = getIntent().getStringExtra("meal");
        mealExclusiveInfoList = (List<MealExclusiveInfo>) getIntent().getSerializableExtra("list");
        topViewManager().moreTextView().setText("换我想吃");
        topViewManager().moreTextView().setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietChangeDietActivity.class);
            startActivity(intent);
        });
        initView();
        topViewManager().titleTextView().setText(titleMeal);
        initValues();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_mall_details, null);
        containerView().addView(view);
        mealTitleTv = view.findViewById(R.id.tv_diet_mall_details_title);
        mealTitleRv = view.findViewById(R.id.rv_diet_mall_details);
    }

    private void initValues() {

        if ("早餐".equals(titleMeal)) {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_bing_with_green, 0, R.drawable.diet_change_my_like, 0);
        } else if ("午餐".equals(titleMeal)) {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.jitui_with_green_20, 0, R.drawable.diet_change_my_like, 0);
        } else {
            mealTitleTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diet_huacai_with_green, 0, R.drawable.diet_change_my_like, 0);
        }

        DietMealOneMealDetailsAdapter adapter = new DietMealOneMealDetailsAdapter(getPageContext(), mealExclusiveInfoList);
        mealTitleRv.setAdapter(adapter);

    }
}
