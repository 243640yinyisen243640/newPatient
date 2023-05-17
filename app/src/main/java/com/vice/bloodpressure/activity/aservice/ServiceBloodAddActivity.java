package com.vice.bloodpressure.activity.aservice;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.BloodThirdInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:timeType
 * 1 空腹
 * 2 早餐后
 * 3 午餐前
 * 4 午餐后
 * 5 晚餐前
 * 6 晚餐后
 * 7 睡前
 * 8 凌晨
 * 描述:血糖添加
 */
public class ServiceBloodAddActivity extends UIBaseActivity implements View.OnClickListener {
    /**
     * 控制目标
     */
    private TextView controlTextView;
    /**
     * 血糖测量时间段
     */
    private TextView timeSlotTextView;
    /**
     * 检测时间
     */
    private TextView timeTextView;
    /**
     * 血糖值
     */
    private TextView valueTextView;
    /**
     * 尺子
     */
    private RulerView rulerView;
    /**
     * 血糖偏低的原点
     */
    private ImageView lowImageView;
    /**
     * 血糖正常的原点
     */
    private ImageView normalImageView;
    /**
     * 血糖偏高的原点
     */
    private ImageView highImageView;

    private LinearLayout sureLinearLayout;

    private int optionAdd;

    private String addTime;

    private String sugarValue;

    private BloodThirdInfo thirdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加血糖数据");
        initView();
        initListener();
    }

    private void initListener() {
        timeSlotTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        rulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                sugarValue = result;
                valueTextView.setText(result);
                //                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                if (thirdInfo == null) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请先选择血糖测量时间段");
                    return;
                }
                sugarValue = result;
                valueTextView.setText(result);
                //                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                if (Double.parseDouble(result) < Double.parseDouble(thirdInfo.getStart())) {
                    valueTextView.setTextColor(Color.parseColor("#264B89F7"));
                    lowImageView.setVisibility(View.VISIBLE);
                    normalImageView.setVisibility(View.GONE);
                    highImageView.setVisibility(View.GONE);
                } else if (Double.parseDouble(thirdInfo.getStart()) <= Double.parseDouble(result) && Double.parseDouble(thirdInfo.getEnd()) >= Double.parseDouble(result)) {
                    valueTextView.setTextColor(Color.parseColor("#2600C27F"));
                    lowImageView.setVisibility(View.GONE);
                    normalImageView.setVisibility(View.VISIBLE);
                    highImageView.setVisibility(View.GONE);
                } else {
                    valueTextView.setTextColor(ContextCompat.getColor(getPageContext(), R.color.red_E5));
                    lowImageView.setVisibility(View.GONE);
                    normalImageView.setVisibility(View.GONE);
                    highImageView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_blood_add, null);
        controlTextView = view.findViewById(R.id.tv_service_blood_add_control);
        timeSlotTextView = view.findViewById(R.id.tv_service_blood_add_time_slot);
        timeTextView = view.findViewById(R.id.tv_service_blood_add_time);
        valueTextView = view.findViewById(R.id.tv_service_blood_add_value);
        rulerView = view.findViewById(R.id.rv_service_blood_add);
        lowImageView = view.findViewById(R.id.iv_service_blood_add_low);
        normalImageView = view.findViewById(R.id.iv_service_blood_add_normal);
        highImageView = view.findViewById(R.id.iv_service_blood_add_high);
        sureLinearLayout = view.findViewById(R.id.ll_service_blood_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_blood_add_time_slot:
                // * 1 空腹
                // * 2 早餐后
                // * 3 午餐前
                // * 4 午餐后
                // * 5 晚餐前
                // * 6 晚餐后
                // * 7 睡前
                // * 8 凌晨
                List<String> timeTypeList = new ArrayList<>();
                timeTypeList.add("空腹");
                timeTypeList.add("早餐后");
                timeTypeList.add("午餐前");
                timeTypeList.add("午餐后");
                timeTypeList.add("晚餐前");
                timeTypeList.add("晚餐后");
                timeTypeList.add("睡前");
                timeTypeList.add("凌晨");
                PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择时间段", timeTypeList, object -> {
                    int option = Integer.parseInt(String.valueOf(object));
                    timeSlotTextView.setText(timeTypeList.get(option));
                    getControlData(option);
                });
                break;
            case R.id.tv_service_blood_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = String.valueOf(object);
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.ll_service_blood_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void getControlData(int option) {
        ToastUtils.getInstance().showProgressDialog(getPageContext(), "请稍后", false);
        Call<String> requestCall = ServiceDataManager.bgTargetByType(UserInfoUtils.getArchivesId(getPageContext()), (option + 1) + "", (call, response) -> {
            ToastUtils.getInstance().dismissProgressDialog();
            if ("0000".equals(response.code)) {
                optionAdd = option;
                thirdInfo = (BloodThirdInfo) response.object;
                thirdInfo.setStart("3.8");
                thirdInfo.setEnd("7.8");
                controlTextView.setText(thirdInfo.getStart() + "-" + thirdInfo.getEnd());
            } else {
                timeSlotTextView.setText("请选择");
            }
        }, (call, t) -> {
            timeSlotTextView.setText("请选择");
        });
        addRequestCallToMap("bgTargetByType", requestCall);
    }


    private void sureToAddData() {
        if (TextUtils.isEmpty(sugarValue)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择血糖测量时间段");
            return;
        }
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择检测时间");
            return;
        }

        Call<String> requestCall = ServiceDataManager.saveMonitorBg(UserInfoUtils.getArchivesId(getPageContext()), (optionAdd + 1) + "", "2", addTime, sugarValue, (call, response) -> {
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
        addRequestCallToMap("saveMonitorBg", requestCall);
    }
}
