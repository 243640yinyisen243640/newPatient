package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:运动添加
 */
public class ServiceExerciseAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView timeTextView;
    private TextView typeTextView;
    private TextView valueTextView;
    private RulerView rulerView;
    private LinearLayout sureLinearLayout;

    private String exerciseValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("运动数据");
        initView();
        initListener();

    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        typeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        rulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                exerciseValue = result;
                //                valueTextView.setText(result);
                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                //                valueTextView.setText(result);
                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_exercise_add, null);
        typeTextView = view.findViewById(R.id.tv_service_exercise_add_type);
        timeTextView = view.findViewById(R.id.tv_service_exercise_add_time);
        valueTextView = view.findViewById(R.id.tv_service_exercise_add_value);
        rulerView = view.findViewById(R.id.rv_service_exercise_add);
        sureLinearLayout = view.findViewById(R.id.ll_service_exercise_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_exercise_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.tv_service_exercise_add_type:
                List<String> exerciseList = new ArrayList<>();
                exerciseList.add("快跑");
                exerciseList.add("快走");
                exerciseList.add("慢跑");
                exerciseList.add("健步走");
                exerciseList.add("羽毛球");

                PickerViewUtils.showChooseSinglePicker(getPageContext(), "有氧运动", exerciseList, object -> {
//                            exerciseChooseTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                            //                            exerciseType = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                        }
                );
                break;
            case R.id.ll_service_temperature_add_sure:
                break;
            default:
                break;
        }
    }
}
