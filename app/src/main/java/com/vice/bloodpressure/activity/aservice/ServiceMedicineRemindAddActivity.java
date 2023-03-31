package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:添加用药提醒
 */
public class ServiceMedicineRemindAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private EditText nameEditText;
    private EditText specsEditText;
    private TextView specsTextView;
    private TextView useTextView;
    private EditText timesEditText;
    private EditText dosageEditText;
    private TextView dosageTextView;
    private TextView timeTextView;
    private LinearLayout sureLinearLayout;

    private String startTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("添加用药提醒");
        initView();
        initListener();
        loadViewManager().changeLoadState(LoadStatus.SUCCESS);
    }

    @Override
    protected void onPageLoad() {

    }

    private void initListener() {
        specsTextView.setOnClickListener(this);
        dosageTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        useTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_medicine_remind_add, null);
        nameEditText = view.findViewById(R.id.et_service_medicine_remind_add_name);
        specsEditText = view.findViewById(R.id.et_service_medicine_remind_add_specs);
        specsTextView = view.findViewById(R.id.tv_service_medicine_remind_add_specs);
        useTextView = view.findViewById(R.id.tv_service_medicine_record_add_use);
        timesEditText = view.findViewById(R.id.et_service_medicine_remind_add_times);
        dosageEditText = view.findViewById(R.id.et_service_medicine_remind_add_dosage);
        dosageTextView = view.findViewById(R.id.tv_service_medicine_remind_add_dosage);
        timeTextView = view.findViewById(R.id.tv_service_medicine_remind_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_medicine_remind_add_sure);
        containerView().addView(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_service_medicine_remind_add_specs:
                chooseTypeWindow("1", "药品规格");
                break;
            case R.id.tv_service_medicine_remind_add_dosage:
                chooseTypeWindow("2", "药品剂量");
                break;
            case R.id.tv_service_medicine_remind_add_time:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        startTime = object.toString();
                        timeTextView.setText(object.toString());
                    }
                });
                break;

            case R.id.tv_service_medicine_record_add_use:
                chooseTypeWindow("3", "药品用法");
                break;
            case R.id.ll_service_medicine_remind_add_sure:
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
        } else if ("2".equals(type)) {
            chooseTypeList.add("mg");
            chooseTypeList.add("g");
            chooseTypeList.add("iu");
            chooseTypeList.add("ml");
            chooseTypeList.add("ug");
        } else {
            chooseTypeList.add("饭前服");
            chooseTypeList.add("饭后服");
            chooseTypeList.add("舌下吞服");
            chooseTypeList.add("口服");
            chooseTypeList.add("水煎服");
            chooseTypeList.add("露化吸乳");
            chooseTypeList.add("喉咙");
            chooseTypeList.add("静滴");
            chooseTypeList.add("肌注");
            chooseTypeList.add("嚼服");
            chooseTypeList.add("冲服");
            chooseTypeList.add("外用");
            chooseTypeList.add("外敷");
            chooseTypeList.add("外洗");
            chooseTypeList.add("皮下注射");
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
