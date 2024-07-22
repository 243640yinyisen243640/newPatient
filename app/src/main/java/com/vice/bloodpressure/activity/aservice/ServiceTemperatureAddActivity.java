package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

import retrofit2.Call;

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

    private String temperatureValue = "";
    private String addTime = "";


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

                valueTextView.setText(result);
//                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                temperatureValue = valueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                valueTextView.setText(result);
//                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                temperatureValue = valueTextView.getText().toString().trim();
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
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = object.toString();
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.ll_service_temperature_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void sureToAddData() {
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择检测时间");
            return;
        }

        Call<String> requestCall = ServiceDataManager.insertMonitorOther(UserInfoUtils.getArchivesId(getPageContext()), "4", "2", addTime, "", "", "", "", "", "", "", temperatureValue, (call, response) -> {
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
        addRequestCallToMap("insertMonitorOther", requestCall);
    }
}
