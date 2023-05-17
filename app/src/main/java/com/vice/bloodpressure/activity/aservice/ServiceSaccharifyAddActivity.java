package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.utils.EditTextUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.SaccharifySeekBar;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:糖化血糖蛋白添加
 */
public class ServiceSaccharifyAddActivity extends UIBaseActivity implements View.OnClickListener {
    private SaccharifySeekBar seekBar;
    private TextView timeTextView;
    private EditText rateEditText;
    private LinearLayout sureLinearLayout;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
    private TextWatcher textWatcher;

    private String addTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加糖化血红蛋白");
        onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int i, boolean b) {
                rateEditText.removeTextChangedListener(textWatcher);
                rateEditText.setText(seekBar.getText(i));
                rateEditText.addTextChangedListener(textWatcher);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                seekBar.setOnSeekBarChangeListener(null);
                int progress = Integer.valueOf((int) (Double.valueOf(s.toString()) * 10));
                seekBar.setProgress(progress);
                seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

                //                BigDecimal discountBigDecimal = new BigDecimal(s.toString()).multiply(new BigDecimal("10.0"));
                //                seekBar.setProgress(discountBigDecimal.toBigInteger().intValue());
            }

        };
        initView();
        initListener();
    }


    private void initListener() {
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        EditTextUtils.decimalNumber(rateEditText, 1);
        rateEditText.addTextChangedListener(textWatcher);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_saccharify_add, null);
        seekBar = view.findViewById(R.id.service_pf_seek);
        rateEditText = view.findViewById(R.id.et_service_saccharify_add_rate);
        timeTextView = view.findViewById(R.id.tv_service_saccharify_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_saccharify_add_sure);
        containerView().addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_saccharify_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, true}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, object -> {
                    addTime = object.toString();
                    timeTextView.setText(object.toString());
                });
                break;
            case R.id.ll_service_saccharify_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void sureToAddData() {
        String sugarValue = rateEditText.getText().toString().trim();
        if (TextUtils.isEmpty(sugarValue)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入糖化值");
            return;
        }

        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择检测时间");
            return;
        }


        Call<String> requestCall = ServiceDataManager.saveMonitorBg(UserInfoUtils.getArchivesId(getPageContext()), "9", "2", addTime, sugarValue, (call, response) -> {
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
