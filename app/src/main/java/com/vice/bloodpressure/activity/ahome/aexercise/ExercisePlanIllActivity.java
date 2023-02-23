package com.vice.bloodpressure.activity.ahome.aexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:运动答题  关于疾病
 */
public class ExercisePlanIllActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private RadioButton oneRadioButton, twoRadioButton, threeRadioButton, fourRadioButton, fiveRadioButton, sixRadioButton;

    private TextView backTv;
    private TextView nextTv;
    private String illType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定运动方案");
        initView();
        initListener();
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (oneRadioButton.getId() == checkedId) {
                illType = "1";
            } else if (twoRadioButton.getId() == checkedId) {
                illType = "2";
            } else if (threeRadioButton.getId() == checkedId) {
                illType = "3";
            } else if (fourRadioButton.getId() == checkedId) {
                illType = "4";
            } else if (fiveRadioButton.getId() == checkedId) {
                illType = "5";
            } else {
                illType = "6";
            }
        });
        backTv.setOnClickListener(v -> finish());
        nextTv.setOnClickListener(v -> startActivity(new Intent(getPageContext(),ExercisePlanExerciseActivity.class)));
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_ill, null);
        radioGroup = view.findViewById(R.id.rg_exercise_ill_type);
        oneRadioButton = view.findViewById(R.id.rb_exercise_ill_type1);
        twoRadioButton = view.findViewById(R.id.rb_exercise_ill_type2);
        threeRadioButton = view.findViewById(R.id.rb_exercise_ill_type3);
        fourRadioButton = view.findViewById(R.id.rb_exercise_ill_type4);
        fiveRadioButton = view.findViewById(R.id.rb_exercise_ill_type5);
        sixRadioButton = view.findViewById(R.id.rb_exercise_ill_type6);
        backTv = view.findViewById(R.id.tv_exercise_ill_back);
        nextTv = view.findViewById(R.id.tv_exercise_ill_next);
        containerView().addView(view);
    }
}
