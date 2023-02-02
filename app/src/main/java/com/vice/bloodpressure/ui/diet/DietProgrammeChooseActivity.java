package com.vice.bloodpressure.ui.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 自定义饮食方案
 */
public class DietProgrammeChooseActivity extends UIBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.diet_programme_choose, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("自定义饮食方案");

        ImageView ivAdd = findViewById(R.id.iv_add);
        ivAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeChooseMealActivity.class);
            intent.putExtra("meal", "早餐");
            startActivity(intent);
        });
    }
}
