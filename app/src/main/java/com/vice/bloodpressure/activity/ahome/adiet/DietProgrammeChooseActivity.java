package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：自定义饮食方案
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2023/2/3 16:55
 */
public class DietProgrammeChooseActivity extends UIBaseActivity implements View.OnClickListener {

    private TextView breakfastTv;
    private TextView lunchTv;
    private TextView dinnerTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();

        topViewManager().titleTextView().setText(R.string.diet_activity_custom_diet_top_title);
        breakfastTv = findViewById(R.id.tv_diet_custom_plan_breakfast);
        lunchTv = findViewById(R.id.tv_diet_custom_plan_lunch);
        dinnerTv = findViewById(R.id.tv_diet_custom_plan_dinner);
        breakfastTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
            intent.putExtra("meal", "早餐");
            startActivity(intent);
        });
    }

    private void initListener() {
        breakfastTv.setOnClickListener(this);
        lunchTv.setOnClickListener(this);
        dinnerTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_diet_programme_choose, null);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_diet_custom_plan_breakfast:
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
