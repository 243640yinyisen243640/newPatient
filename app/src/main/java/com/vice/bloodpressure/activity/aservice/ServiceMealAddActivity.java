package com.vice.bloodpressure.activity.aservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.service.ServiceMealAddListAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceMealAddActivity extends UIBaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_REFRESH = 1;
    private LinearLayout addLinearLayout;
    private TextView stagetTextView;
    private TextView timeTextView;
    private NoScrollListView mealLv;
    /**
     * 热量图标
     */
    private ImageView fireImageView;
    /**
     * 热量数字
     */
    private TextView fireTextView;
    private LinearLayout sureLinearLayout;

    /**
     * 1:早餐  2：午餐  3：晚餐
     */
    private String eatPoint = "";

    private String addTime = "";
    /**
     * 食物名称
     */
    private String foodName = "";
    /**
     * 食物卡路里
     */
    private String foodBigCards = "";
    /**
     * 食物重量
     */
    private String foodWeight = "";

    private ServiceMealAddListAdapter addListAdapter;

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
        mealLv = view.findViewById(R.id.lv_service_meal);
        fireImageView = view.findViewById(R.id.iv_service_meal_all_fire);
        fireTextView = view.findViewById(R.id.tv_service_meal_all_fire);
        sureLinearLayout = view.findViewById(R.id.ll_service_meal_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_service_meal_add:
                Intent intent = new Intent(getPageContext(), ServiceMealChooseActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_REFRESH);
                break;
            case R.id.tv_service_meal_add_stage:
                chooseTypeWindow();
                break;
            case R.id.tv_service_meal_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                        timeTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.ll_service_meal_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void sureToAddData() {
        if (TextUtils.isEmpty(foodName)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请添加食物");
            return;
        }
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择检测时间");
            return;
        }

        Call<String> requestCall = ServiceDataManager.mealAdd(UserInfoUtils.getArchivesId(getPageContext()), "", "2", eatPoint, addTime, foodName, foodBigCards, foodWeight, (call, response) -> {
            if ("0000".equals(response.code)) {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                setResult(RESULT_OK);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("mealAdd", requestCall);
    }


    private void chooseTypeWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("早餐");
        exerciseList.add("午餐");
        exerciseList.add("晚餐");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "饮食阶段", exerciseList, object -> {
                    stagetTextView.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    eatPoint = Integer.parseInt(String.valueOf(object)) + 1 + "";
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_FOR_REFRESH:
                    Log.i("yys", "REQUEST_CODE_FOR_REFRESH");
                    //拿到食物名称
                    if (data != null) {
                        List<MealChildInfo> tempList = (List<MealChildInfo>) data.getSerializableExtra("tempList");
                        addListAdapter = new ServiceMealAddListAdapter(getPageContext(), tempList);
                        mealLv.setAdapter(addListAdapter);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
