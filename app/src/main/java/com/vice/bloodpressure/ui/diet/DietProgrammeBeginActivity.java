package com.vice.bloodpressure.ui.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 饮食方案开始
 */
public class DietProgrammeBeginActivity extends UIBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.intelligence_diet_programme_begin, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("饮食方案");
        TextView tvDietProgrammeBegin = findViewById(R.id.tv_diet_programme_begin);
        //开启智能体验
        tvDietProgrammeBegin.setOnClickListener(v -> startActivity(new Intent(getPageContext(), DietProgrammeOneActivity.class)));
    }

}
