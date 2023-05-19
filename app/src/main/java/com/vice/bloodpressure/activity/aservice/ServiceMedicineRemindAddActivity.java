package com.vice.bloodpressure.activity.aservice;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.vice.bloodpressure.datamanager.ServiceDataManager;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.utils.DataUtils;
import com.vice.bloodpressure.utils.EditTextUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type 1：编辑 2：查看 3添加
 * 描述:添加用药提醒
 */
public class ServiceMedicineRemindAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
    /**
     * 药品名称
     */
    private EditText nameEditText;
    /**
     * 药品规格
     */
    private EditText specsEditText;
    private TextView specsTextView;
    private LinearLayout specsLinearLayout;
    /**
     * 药品用法
     */
    private TextView useTextView;
    /**
     * 药品数量
     */
    private TextView numEditText;
    /**
     * 药品次数
     */
    private EditText timesEditText;
    /**
     * 药品剂量
     */
    private EditText dosageEditText;
    private TextView dosageTextView;
    /**
     * 提醒时间
     */
    private TextView timeTextView;
    private LinearLayout sureLinearLayout;

    private String addTime = "";
    /**
     * 1：编辑 2：查看 3添加
     */
    private String type;
    /**
     * 药品ID
     */
    private String pkId = "";

    private String medicineUse = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        pkId = getIntent().getStringExtra("pkId");
        initView();
        initListener();

        if ("1".equals(type)) {
            topViewManager().titleTextView().setText("编辑用药提醒");
            loadViewManager().changeLoadState(LoadStatus.LOADING);
            nameEditText.setEnabled(false);
            sureLinearLayout.setVisibility(View.VISIBLE);
            isCanClick(true);
        } else if ("2".equals(type)) {
            topViewManager().titleTextView().setText("用药提醒详情");
            loadViewManager().changeLoadState(LoadStatus.LOADING);
            sureLinearLayout.setVisibility(View.GONE);
            nameEditText.setEnabled(false);
            isCanClick(false);
        } else {
            topViewManager().titleTextView().setText("添加用药提醒");
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            nameEditText.setEnabled(true);
            isCanClick(true);
            sureLinearLayout.setVisibility(View.VISIBLE);
        }

    }

    private void isCanClick(boolean isCanClick) {
        specsEditText.setEnabled(isCanClick);
        timesEditText.setEnabled(isCanClick);
        dosageEditText.setEnabled(isCanClick);
        timeTextView.setEnabled(isCanClick);
        useTextView.setEnabled(isCanClick);
        numEditText.setEnabled(isCanClick);
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.drugWarnAppLook(pkId == null ? "" : pkId, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                HealthyDataChildInfo allInfo = (HealthyDataChildInfo) response.object;
                bindData(allInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("drugWarnAppLook", requestCall);
    }

    private void bindData(HealthyDataChildInfo allInfo) {
        nameEditText.setText(allInfo.getDrugName());
        specsEditText.setText(allInfo.getDrugSpec());
        specsTextView.setText(allInfo.getDrugSpecUnit());
        numEditText.setText(allInfo.getDrugNumber());
        //(1饭前服,2饭后服,3舌下含服,4口服,5水煎服,6露化吸乳,7喉咙,8静滴,9肌注,10嚼服,11冲服,12外用,13外敷,14外洗,15皮下注射)
        if ("1".equals(allInfo.getDrugMode())) {
            useTextView.setText("饭前服");
        } else if ("2".equals(allInfo.getDrugMode())) {
            useTextView.setText("饭后服");
        } else if ("3".equals(allInfo.getDrugMode())) {
            useTextView.setText("舌下含服");
        } else if ("4".equals(allInfo.getDrugMode())) {
            useTextView.setText("口服");
        } else if ("5".equals(allInfo.getDrugMode())) {
            useTextView.setText("水煎服");
        } else if ("6".equals(allInfo.getDrugMode())) {
            useTextView.setText("露化吸乳");
        } else if ("7".equals(allInfo.getDrugMode())) {
            useTextView.setText("喉咙");
        } else if ("8".equals(allInfo.getDrugMode())) {
            useTextView.setText("静滴");
        } else if ("9".equals(allInfo.getDrugMode())) {
            useTextView.setText("肌注");
        } else if ("10".equals(allInfo.getDrugMode())) {
            useTextView.setText("嚼服");
        } else if ("11".equals(allInfo.getDrugMode())) {
            useTextView.setText("冲服");
        } else if ("12".equals(allInfo.getDrugMode())) {
            useTextView.setText("外用");
        } else if ("13".equals(allInfo.getDrugMode())) {
            useTextView.setText("外敷");
        } else if ("14".equals(allInfo.getDrugMode())) {
            useTextView.setText("外洗");
        } else {
            useTextView.setText("皮下注射");
        }

        timesEditText.setText(allInfo.getDrugTimes());
        dosageEditText.setText(allInfo.getDrugDose());
        dosageTextView.setText(allInfo.getDrugDoseUnit());
        timeTextView.setText(DataUtils.changeDataFormat(DataFormatManager.TIME_FORMAT_Y_M_D_H_M_S, DataFormatManager.TIME_FORMAT_Y_M_D, allInfo.getWranTime()));
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
        specsLinearLayout = view.findViewById(R.id.ll_service_medicine_remind_add_specs);
        useTextView = view.findViewById(R.id.tv_service_medicine_record_add_use);
        numEditText = view.findViewById(R.id.et_service_medicine_remind_add_num);
        timesEditText = view.findViewById(R.id.et_service_medicine_remind_add_times);
        dosageEditText = view.findViewById(R.id.et_service_medicine_remind_add_dosage);
        dosageTextView = view.findViewById(R.id.tv_service_medicine_remind_add_dosage);
        timeTextView = view.findViewById(R.id.tv_service_medicine_remind_add_time);
        sureLinearLayout = view.findViewById(R.id.ll_service_medicine_remind_add_sure);
        EditTextUtils.decimalNumber(dosageEditText, 1);
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
                        addTime = object.toString();
                        timeTextView.setText(object.toString());
                    }
                });
                break;

            case R.id.tv_service_medicine_record_add_use:
                chooseTypeWindow("3", "药品用法");
                break;
            case R.id.ll_service_medicine_remind_add_sure:
                sureToAddData();
                break;
            default:
                break;
        }
    }

    private void sureToAddData() {
        String medicineName = nameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(medicineName)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入药品名称");
            return;
        }
        String medicineSpecs = specsEditText.getText().toString().trim();
        if (TextUtils.isEmpty(medicineSpecs)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入规格数量");
            return;
        }
        String medicineNum = numEditText.getText().toString().trim();
        if (TextUtils.isEmpty(medicineNum)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入药品数量");
            return;
        }
        if (TextUtils.isEmpty(medicineUse)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择药品用法");
            return;
        }

        String medicineTimes = timesEditText.getText().toString().trim();
        if (TextUtils.isEmpty(medicineTimes)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入服药次数");
            return;
        }
        String medicineDosage = dosageEditText.getText().toString().trim();
        if (TextUtils.isEmpty(medicineDosage)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入服药剂量");
            return;
        }
        if (TextUtils.isEmpty(addTime)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择开始时间");
            return;
        }

        Call<String> requestCall = ServiceDataManager.drugWarnAppAdd(UserInfoUtils.getArchivesId(getPageContext()), pkId == null ? "" : pkId, "2", medicineName, medicineSpecs, specsTextView.getText().toString().trim(), medicineNum, medicineUse, medicineTimes, medicineDosage, dosageTextView.getText().toString().trim(), addTime + " 00:00:00", (call, response) -> {
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
        addRequestCallToMap("medicineAdd", requestCall);
    }

    /**
     * @param type  1 规格  2剂量
     * @param title 标题
     */
    private void chooseTypeWindow(String type, String title) {
        List<String> chooseTypeList = new ArrayList<>();
        if ("1".equals(type)) {
            chooseTypeList.add("盒");
            chooseTypeList.add("瓶");
            chooseTypeList.add("片");
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
                    } else if ("2".equals(type)) {
                        dosageTextView.setText(chooseTypeList.get(Integer.parseInt(String.valueOf(object))));
                    } else {
                        useTextView.setText(chooseTypeList.get(Integer.parseInt(String.valueOf(object))));
                        medicineUse = (Integer.parseInt(String.valueOf(object)) + 1) + "";
                    }
                }
        );
    }
}
