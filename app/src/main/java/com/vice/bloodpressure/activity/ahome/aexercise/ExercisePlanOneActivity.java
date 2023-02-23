package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：
 * 传参：
 * 描述: 运动答题 运动禁忌症
 * 作者: beauty
 * 创建日期: 2023/2/23 11:28
 */
public class ExercisePlanOneActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView unFitTv;
    private TextView fitTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initListener();

    }

    private void initListener() {
        unFitTv.setOnClickListener(this);
        fitTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_one, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("制定运动方案");

        unFitTv = view.findViewById(R.id.tv_exercise_plan_un_fit);
        fitTv = view.findViewById(R.id.tv_exercise_plan_fit);


        TextView progress = view.findViewById(R.id.tv_exercise_plan_one_progress);
        setTextStyle(progress, 1.3f, 0, 1);
    }

    private void setTextStyle(TextView textView, float proportion, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exercise_plan_un_fit:
                finish();
                break;
            case R.id.tv_exercise_plan_fit:
                startActivity(new Intent(getPageContext(), ExercisePlanAnswerAgeActivity.class));
                break;
            default:
                break;
        }
    }
}
