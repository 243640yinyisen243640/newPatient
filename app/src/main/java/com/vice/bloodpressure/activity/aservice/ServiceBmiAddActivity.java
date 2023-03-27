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

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:bmi添加
 */
public class ServiceBmiAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView controlTextView;
    private TextView heightValueTextView;
    private RulerView heightRulerView;
    private TextView timeTextView;
    private TextView weightValueTextView;
    private RulerView weightRulerView;
    private LinearLayout sureLinearLayout;

    private String heightValue;
    private String weightValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加BMI数据");
        initView();
        initListener();

    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        heightRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                heightValue = result;
                //                sysValueTextView.setText(result);
                heightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                //                sysValueTextView.setText(result);
                heightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
        weightRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                weightValue = result;
                //                diaValueTextView.setText(result);
                weightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                //                diaValueTextView.setText(result);
                weightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_bmi_add, null);
        controlTextView = view.findViewById(R.id.tv_service_bmi_add_control);
        heightValueTextView = view.findViewById(R.id.tv_service_bmi_add_height_value);
        heightRulerView = view.findViewById(R.id.rv_service_bmi_height_add);
        weightValueTextView = view.findViewById(R.id.tv_service_bmi_weight_value);
        weightRulerView = view.findViewById(R.id.rv_service_bmi_weight_add);
        timeTextView = view.findViewById(R.id.tv_service_bmi_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_bmi_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_bmi_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_bmi_add_sure:
                break;
            default:
                break;
        }
    }
}
