package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:血糖添加
 */
public class ServiceBloodAddActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView controlTextView;
    private TextView timeSlotTextView;
    private TextView timeTextView;
    private TextView valueTextView;
    private RulerView rulerView;
    private ImageView lowImageView;
    private ImageView normalImageView;
    private ImageView highImageView;
    private LinearLayout sureLinearLayout;

    private String bloodValue;


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
                bloodValue = result;
                valueTextView.setText(result);
                //                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                valueTextView.setText(result);
                //                valueTextView.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
                if (Double.parseDouble(result) < 3.8) {
                    lowImageView.setVisibility(View.VISIBLE);
                    normalImageView.setVisibility(View.GONE);
                    highImageView.setVisibility(View.GONE);
                } else if (3.8 <= Double.parseDouble(result) && 7.8 >= Double.parseDouble(result)) {
                    lowImageView.setVisibility(View.GONE);
                    normalImageView.setVisibility(View.VISIBLE);
                    highImageView.setVisibility(View.GONE);
                } else {
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
                break;
            case R.id.tv_service_blood_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, true, true, false}, DataFormatManager.TIME_FORMAT_Y_M_D_H_M, new CallBack() {
                    @Override
                    public void callBack(Object object) {

                    }
                });
                break;
            case R.id.ll_service_blood_add_sure:
                break;
            default:
                break;
        }
    }
}
