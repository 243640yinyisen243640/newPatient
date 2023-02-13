package com.vice.bloodpressure.activity.ahome.adiet;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:饮食方案详细
 */
public class DietMealPlanDetailsActivity extends UIBaseLoadActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        View view = View.inflate(getPageContext(), R.layout.activity_meal_plan_details, null);
        containerView().addView(view);
    }


    @Override
    protected void onPageLoad() {

    }
}
