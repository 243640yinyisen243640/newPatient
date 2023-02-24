package com.vice.bloodpressure.activity.ahome.aexercise;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:添加运动  跳绳 太极拳  跑步
 */
public class ExercisePlanAddRecordActivity extends UIBaseLoadActivity {
    private TextView timeTv;
    private TextView fireTv;
    private TextView typeTv;
    private TextView timeChooseTv;
    private RulerView timeRv;
    private LinearLayout tiaoshengLl;
    private LinearLayout taijiLi;
    private TextView sureTextView;

    private String exerciseTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加运动记录");
        initView();
        initListener();
        initValue();
    }

    private void initValue() {
        timeRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                exerciseTime = result;
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                timeChooseTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                timeTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
    }

    private void initListener() {
        typeTv.setOnClickListener(v -> {
            chooseTypeWindow();
        });
        sureTextView.setOnClickListener(v -> {

        });
    }

    /**
     * 选择运动类型
     */
    private void chooseTypeWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("跑步");
        exerciseList.add("跳绳");
        exerciseList.add("太极拳");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择运动", exerciseList, object ->
        {
            Log.i("yys","type==="+exerciseList.get(Integer.parseInt(String.valueOf(object))));
            Log.i("yys","type2==="+object.toString());
            typeTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
            if (TextUtils.equals(exerciseList.get(Integer.parseInt(String.valueOf(object))), "太极拳")) {
                taijiLi.setVisibility(View.VISIBLE);
                tiaoshengLl.setVisibility(View.GONE);
            } else if (TextUtils.equals(exerciseList.get(Integer.parseInt(String.valueOf(object))), "跳绳")) {
                taijiLi.setVisibility(View.GONE);
                tiaoshengLl.setVisibility(View.VISIBLE);
            } else {
                taijiLi.setVisibility(View.GONE);
                tiaoshengLl.setVisibility(View.GONE);
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_exercise_record_add, null);
        timeTv = view.findViewById(R.id.tv_exercise_record_add_time);
        fireTv = view.findViewById(R.id.tv_exercise_record_add_fire);
        typeTv = view.findViewById(R.id.tv_exercise_record_add_type);
        timeChooseTv = view.findViewById(R.id.tv_exercise_record_add_time_choose);
        timeRv = view.findViewById(R.id.rv_exercise_record_add_time);
        tiaoshengLl = view.findViewById(R.id.ll_exercise_record_add_tiaosheng);
        taijiLi = view.findViewById(R.id.ll_exercise_record_add_taiji);
        sureTextView = view.findViewById(R.id.tv_exercise_record_add_sure);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
