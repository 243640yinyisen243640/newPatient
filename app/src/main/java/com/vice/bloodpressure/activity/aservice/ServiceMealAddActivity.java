package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceMealAddActivity extends UIBaseActivity implements View.OnClickListener {
    private LinearLayout addLinearLayout;
    private TextView stagetTextView;
    private TextView timeTextView;
    private RecyclerView mealRv;
    private ImageView fireImageView;
    private TextView fireTextView;
    private LinearLayout sureLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加饮食数据");
        initView();
        initlistener();
    }

    private void initlistener() {
        addLinearLayout.setOnClickListener(this);
        stagetTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_meal_add, null);
        addLinearLayout = view.findViewById(R.id.ll_service_meal_add);
        stagetTextView = view.findViewById(R.id.tv_service_meal_add_stage);
        timeTextView = view.findViewById(R.id.tv_service_meal_add_time);
        mealRv = view.findViewById(R.id.rv_service_meal);
        fireImageView = view.findViewById(R.id.iv_service_meal_all_fire);
        fireTextView = view.findViewById(R.id.tv_service_meal_all_fire);
        sureLinearLayout = view.findViewById(R.id.ll_service_meal_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_service_meal_add:
                startActivity(new Intent(getPageContext(), ServiceMealChooseActivity.class));
                break;
            case R.id.tv_service_meal_add_stage:
                chooseTypeWindow();
                break;
            case R.id.tv_service_meal_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        timeTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.ll_service_meal_add_sure:
                break;
            default:
                break;
        }
    }

    private void chooseTypeWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("早餐");
        exerciseList.add("午餐");
        exerciseList.add("晚餐");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "饮食阶段", exerciseList, object -> {
                    stagetTextView.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    //                    exerciseType = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                }
        );
    }
}
