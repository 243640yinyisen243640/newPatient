package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.math.BigDecimal;

import retrofit2.Call;

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

    private String heightValue = "";
    private String weightValue = "";

    private String addTime = "";


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

                //                sysValueTextView.setText(result);
                heightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                heightValue = heightValueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                //                sysValueTextView.setText(result);
                heightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                heightValue = heightValueTextView.getText().toString().trim();
            }
        });
        weightRulerView.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {

                //                diaValueTextView.setText(result);
                weightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                weightValue = weightValueTextView.getText().toString().trim();
            }

            @Override
            public void onScrollResult(String result) {
                //                diaValueTextView.setText(result);
                weightValueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                weightValue = weightValueTextView.getText().toString().trim();
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
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        addTime = object.toString();
                        timeTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.ll_service_bmi_add_sure:
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

        Call<String> requestCall = ServiceDataManager.insertMonitorOther(UserInfoUtils.getArchivesId(getPageContext()), "2", "2", addTime, "", "", "", heightValue, weightValue, "", "", "", (call, response) -> {
            if ("0000".equals(response.code)) {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
                setResult(RESULT_OK);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ToastUtils.getInstance().showToast(getPageContext(), "失败");
        });
        addRequestCallToMap("insertMonitorOther", requestCall);
    }
}
