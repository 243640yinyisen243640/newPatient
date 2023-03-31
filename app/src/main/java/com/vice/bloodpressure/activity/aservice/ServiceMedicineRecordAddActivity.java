package com.vice.bloodpressure.activity.aservice;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:添加用药
 */
public class ServiceMedicineRecordAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private EditText nameEditText;
    private EditText specsEditText;
    private TextView specsTextView;
    private EditText timesEditText;
    private EditText dosageEditText;
    private TextView dosageTextView;
    private TextView startTextView;
    private TextView endTextView;
    private LinearLayout sureLinearLayout;

    private String startTime;

    @Override
    protected void onPageLoad() {
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
    }

    private void initListener() {
        specsTextView.setOnClickListener(this);
        dosageTextView.setOnClickListener(this);
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_medicine_record_add, null);
        nameEditText = view.findViewById(R.id.et_service_medicine_record_add_name);
        specsEditText = view.findViewById(R.id.et_service_medicine_record_add_specs);
        specsTextView = view.findViewById(R.id.tv_service_medicine_record_add_specs);
        timesEditText = view.findViewById(R.id.et_service_medicine_record_add_times);
        dosageEditText = view.findViewById(R.id.et_service_medicine_record_add_dosage);
        dosageTextView = view.findViewById(R.id.tv_service_medicine_record_add_dosage);
        startTextView = view.findViewById(R.id.tv_service_medicine_record_add_start);
        endTextView = view.findViewById(R.id.tv_service_medicine_record_add_end);
        sureLinearLayout = view.findViewById(R.id.ll_service_medicine_record_add_sure);
        containerView().addView(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_medicine_record_add_specs:
                chooseTypeWindow("1", "药品规格");
                break;
            case R.id.tv_service_medicine_record_add_dosage:
                chooseTypeWindow("2", "药品剂量");
                break;
            case R.id.tv_service_medicine_record_add_start:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = object.toString();
                        startTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_service_medicine_record_add_end:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        if (XyTimeUtils.compareTwoTime(startTime, object.toString())) {
                            endTextView.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.ll_service_medicine_record_add_sure:
                break;
            default:
                break;
        }
    }

    /**
     * @param type  1 规格  2剂量
     * @param title 标题
     */
    private void chooseTypeWindow(String type, String title) {
        List<String> chooseTypeList = new ArrayList<>();
        if ("1".equals(type)) {
            chooseTypeList.add("/盒");
            chooseTypeList.add("/瓶");
            chooseTypeList.add("/片");
        } else {
            chooseTypeList.add("mg");
            chooseTypeList.add("ml");
        }

        PickerViewUtils.showChooseSinglePicker(getPageContext(), title, chooseTypeList, object -> {
                    if ("1".equals(type)) {
                        specsTextView.setText(chooseTypeList.get(Integer.parseInt(String.valueOf(object))));
                    } else {
                        dosageTextView.setText(chooseTypeList.get(Integer.parseInt(String.valueOf(object))));
                    }

                }
        );
    }
}
