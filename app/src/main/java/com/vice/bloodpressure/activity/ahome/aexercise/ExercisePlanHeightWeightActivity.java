package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

import java.math.BigDecimal;

/**
 * 类名：
 * 传参：
 * 描述: 运动答题 身高体重
 * 作者: beauty
 */
public class ExercisePlanHeightWeightActivity extends UIBaseActivity {
    //身高
    private String height = "160";
    //体重
    private String weight = "60";

    private RulerView heightRv;
    private RulerView weightRv;

    private TextView heightResultTv;
    private TextView weightResultTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定运动方案");

        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_height_weight, null);
        heightRv = view.findViewById(R.id.rv_exercise_height);
        weightRv = view.findViewById(R.id.rv_exercise_weight);
        heightResultTv = view.findViewById(R.id.tv_exercise_height_result);
        weightResultTv = view.findViewById(R.id.tv_exercise_weight_result);
        TextView nextStepTv = view.findViewById(R.id.tv_exercise_hw_next);
        containerView().addView(view);
        heightRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                height = result;
                heightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                heightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });

        weightRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                weight = result;
                weightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                weightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });



        //下一步
        nextStepTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), ExercisePlanIllActivity.class);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            startActivity(intent);
        });

        TextView backTv = findViewById(R.id.tv_exercise_hw_back);
        backTv.setOnClickListener(v -> finish());

        TextView progress = findViewById(R.id.tv_diet_programme_one_progress);
        setTextStyle(progress, 1.3f, 0, 1);
    }

    private void setTextStyle(TextView textView, float proportion, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

}
