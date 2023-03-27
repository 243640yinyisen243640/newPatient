package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
 * 描述:血压添加
 */
public class ServicePressureAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView controlTextView;
    private TextView sysValueTextView;
    private RulerView sysRulerView;
    private TextView timeTextView;
    private TextView diaValueTextView;
    private RulerView diaRulerView;
    private EditText rateEditText;
    private LinearLayout sureLinearLayout;

    private String sysValue;
    private String diaValue;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加血压数据");
        initView();
        initListener();

    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        sysRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                sysValue = result;
                //                sysValueTextView.setText(result);
                sysValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                //                sysValueTextView.setText(result);
                sysValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
        diaRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                diaValue = result;
                //                diaValueTextView.setText(result);
                diaValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                //                diaValueTextView.setText(result);
                diaValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_pressure_add, null);
        controlTextView = view.findViewById(R.id.tv_service_pressure_add_control);
        sysValueTextView = view.findViewById(R.id.tv_service_pressure_add_sys_value);
        sysRulerView = view.findViewById(R.id.rv_service_pressure_sys_add);
        diaValueTextView = view.findViewById(R.id.tv_service_pressure_add_dia_value);
        diaRulerView = view.findViewById(R.id.rv_service_pressure_dia_add);
        rateEditText = view.findViewById(R.id.et_service_pressure_add_rate);
        timeTextView = view.findViewById(R.id.tv_service_pressure_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_pressure_add_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_pressure_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_pressure_add_sure:
                break;
            default:
                break;
        }
    }
}
