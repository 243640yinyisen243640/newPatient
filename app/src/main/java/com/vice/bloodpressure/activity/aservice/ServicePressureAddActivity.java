package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.math.BigDecimal;

import retrofit2.Call;

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
    /**
     * 收缩压
     */
    private String sysValue = "";
    /**
     * 舒张压
     */
    private String diaValue = "";
    /**
     * 时间
     */
    private String addTime = "";

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

                //                sysValueTextView.setText(result);
                sysValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                sysValue = sysValueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                //                sysValueTextView.setText(result);
                sysValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                sysValue = sysValueTextView.getText().toString().trim();
            }
        });
        diaRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {

                //                diaValueTextView.setText(result);
                diaValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                diaValue = diaValueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                //                diaValueTextView.setText(result);
                diaValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                diaValue = diaValueTextView.getText().toString().trim();
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
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = object.toString();
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.ll_service_pressure_add_sure:
                addPresureData();
                break;
            default:
                break;
        }
    }

    private void addPresureData() {
        String heartRate = rateEditText.getText().toString().trim();
//        if (TextUtils.isEmpty(heartRate)) {
//            ToastUtils.getInstance().showToast(getPageContext(), "请输入心率");
//            return;
//        }

        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择时间");
            return;
        }
        Call<String> requestCall = ServiceDataManager.insertMonitorHtn(UserInfoUtils.getArchivesId(getPageContext()), "2", sysValue, diaValue, heartRate, addTime, (call, response) ->
        {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("insertMonitorHtn", requestCall);
    }
}
