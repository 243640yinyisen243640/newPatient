package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.view.SaccharifySeekBar;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加糖化血红蛋白");
        initView();
        initListener();
//        initValues();
    }

    private void initValues() {
//        seekBar.setTextSize(25);// 设置字体大小
    }

    private void initListener() {
        timeTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //                if (seekBar.getProgress() == 100) {
                //                    noDiscountTextView.setVisibility(View.VISIBLE);
                //                } else {
                //                    noDiscountTextView.setVisibility(View.INVISIBLE);
                //                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_saccharify_add_sure:
                break;
            default:
                break;
        }
    }
}
