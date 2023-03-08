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
 * 描述: 运动答题 年龄
 * 作者: beauty
 * 创建日期: 2023/2/23 11:27
 */
public class ExercisePlanAnswerAgeActivity extends UIBaseActivity implements View.OnClickListener {
    //年龄
    private String age = "30";


    private RulerView ageRv;

    private TextView resultTv;
    private TextView backTv;
    private TextView nextTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定运动方案");
        initView();
        initListener();

        ageRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                age = result;
                resultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                resultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });


    }

    private void initListener() {
        backTv.setOnClickListener(this);
        nextTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_age, null);
        ageRv = view.findViewById(R.id.rv_exercise_plan_ruler);
        resultTv = view.findViewById(R.id.tv_exercise_plan_result);
        backTv = view.findViewById(R.id.tv_exercise_plan_back);
        nextTv = view.findViewById(R.id.tv_exercise_plan_next);
        TextView progress = view.findViewById(R.id.tv_exercise_plan_two_progress);
        setTextStyle(progress, 1.3f, 0, 1);
        containerView().addView(view);
    }

    private void setTextStyle(TextView textView, float proportion, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exercise_plan_next:
                startActivity(new Intent(getPageContext(), ExercisePlanHeightWeightActivity.class));
                break;
            case R.id.tv_exercise_plan_back:
                finish();
                break;
            default:
                break;

        }
    }
}
