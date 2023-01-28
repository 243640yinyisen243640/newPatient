package com.vice.bloodpressure.ui.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

import java.math.BigDecimal;

public class DietProgrammeOneActivity extends UIBaseActivity {
    //身高
    private String height = "160";
    //体重
    private String weight = "60";

    private RulerView rvHeight;
    private RulerView rvWeight;

    private TextView tvHeightResult;
    private TextView tvWeightResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.intelligence_activity_diet_programme_one, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("饮食方案");

        rvHeight = findViewById(R.id.rulerView_height);
        rvWeight = findViewById(R.id.rulerView_weight);
        tvHeightResult = findViewById(R.id.tv_rule_height_result);
        tvWeightResult = findViewById(R.id.tv_rule_weight_result);

        rvHeight.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                height = result;
                tvHeightResult.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                tvHeightResult.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });

        rvWeight.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                weight = result;
                tvWeightResult.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                tvWeightResult.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });


        TextView tvNextStep = findViewById(R.id.tv_diet_programme_one_next_step);
        //下一步
        tvNextStep.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), DietProgrammeTwoActivity.class);
            intent.putExtra("height",height);
            intent.putExtra("weight",weight);
            startActivity(intent);
        });

        TextView progress = findViewById(R.id.tv_diet_programme_one_progress);
        setTextStyle(progress,1.3f,0,1);
    }

}
