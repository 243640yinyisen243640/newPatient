package com.vice.bloodpressure.activity.ahome.aexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.pop.ExercisePlanSuccessPopupWindow;
import com.vice.bloodpressure.utils.ToastUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:运动答题  关于运动习惯
 */
public class ExercisePlanExerciseActivity extends UIBaseActivity {
    private CheckBox habitYesCb;
    private CheckBox habitNoCb;
    private CheckBox emptyYesCb;
    private CheckBox emptyNoCb;
    private EditText timeEt;
    private EditText rateEt;
    private TextView nextTv;

    private ExercisePlanSuccessPopupWindow successPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定运动方案");
        initView();
        initListener();
        initValues();
    }

    private void initValues() {

    }

    private void initListener() {
        nextTv.setOnClickListener(v -> {
            String time = timeEt.getText().toString().trim();
            if (TextUtils.isEmpty(time)) {
                ToastUtils.getInstance().showToast(getPageContext(), "请输入运动时间");
                return;
            }
            String rate = rateEt.getText().toString().trim();
            if (TextUtils.isEmpty(rate)) {
                ToastUtils.getInstance().showToast(getPageContext(), "请输入运动频率");
                return;
            }
            if (successPopupWindow == null) {
                successPopupWindow = new ExercisePlanSuccessPopupWindow(getPageContext(), v1 -> {
                    //点击确定的操作
                });
            }
            TextView textView = successPopupWindow.showContent();
            SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

            stringBuilder.append(getString(R.string.exercise_success_height));
            int length1 = stringBuilder.length();
            stringBuilder.append(String.format(getPageContext().getString(R.string.exercise_success_height_num), "肥胖"));
            int length2 = stringBuilder.length();
            stringBuilder.append(String.format(getPageContext().getString(R.string.exercise_success_weight),"增重", "10","减重"));
            stringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#00C27F")), length1, length2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            textView.setText(stringBuilder);

            successPopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.CENTER);
        });

        habitYesCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                habitNoCb.setChecked(false);
            } else {
                habitNoCb.setChecked(true);
            }
        });
        habitNoCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                habitYesCb.setChecked(false);
            } else {
                habitYesCb.setChecked(true);
            }
        });
        emptyYesCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                emptyNoCb.setChecked(false);
            } else {
                emptyNoCb.setChecked(true);
            }
        });
        emptyNoCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                emptyYesCb.setChecked(false);
            } else {
                emptyYesCb.setChecked(true);
            }
        });


        String ha = habitYesCb.isChecked() ? "1" : "0";
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_plan_exercise, null);
        habitYesCb = view.findViewById(R.id.cb_exercise_exercise_habit_yes);
        habitNoCb = view.findViewById(R.id.cb_exercise_exercise_habit_no);
        emptyYesCb = view.findViewById(R.id.cb_exercise_exercise_empty_yes);
        emptyNoCb = view.findViewById(R.id.cb_exercise_exercise_empty_no);
        timeEt = view.findViewById(R.id.et_exercise_exercise_time);
        rateEt = view.findViewById(R.id.et_exercise_exercise_rate);
        nextTv = view.findViewById(R.id.tv_exercise_exercise_next);
        containerView().addView(view);
    }
}
