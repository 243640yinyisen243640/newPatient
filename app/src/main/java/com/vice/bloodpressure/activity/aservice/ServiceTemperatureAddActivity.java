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
 * 描述:体温添加
 */
public class ServiceTemperatureAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView timeTextView;
    private TextView valueTextView;
    private RulerView rulerView;
    private LinearLayout sureLinearLayout;

    private String temperatureValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加体温数据");
        initView();
        initListener();

    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        rulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                temperatureValue = result;
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
        View view = View.inflate(getPageContext(), R.layout.activity_service_temperature_add, null);
        timeTextView = view.findViewById(R.id.tv_service_temperature_add_time);
        valueTextView = view.findViewById(R.id.tv_service_temperature_add_value);
        rulerView = view.findViewById(R.id.rv_service_temperature_add);
        sureLinearLayout = view.findViewById(R.id.ll_service_temperature_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_temperature_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_temperature_add_sure:
                break;
            default:
                break;
        }
    }
}
