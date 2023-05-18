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
import com.vice.bloodpressure.model.HealthyDataAllInfo;
import com.vice.bloodpressure.utils.EditTextUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.utils.XyTimeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:type 1：编辑 2：查看
 * 描述:添加用药
 */
public class ServiceMedicineRecordAddActivity extends UIBaseLoadActivity implements View.OnClickListener {
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
     * 药服药次数
     */
    private EditText timesEditText;
    /**
     * 药品剂量
     */
    private EditText dosageEditText;
    private TextView dosageTextView;
    /**
     * 筛选的开始结束时间
     */
    private TextView startTextView;
    private TextView endTextView;
    /**
     * 确认按钮
     */
    private LinearLayout sureLinearLayout;

    private String startTime = "";
    private String endTime = "";
    /**
     * 1：编辑 2：查看 3添加
     */
    private String type;
    /**
     * 药品ID
     */
    private String pkId = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        pkId = getIntent().getStringExtra("pkId");
        initView();
        initListener();

        if ("1".equals(type)) {
            topViewManager().titleTextView().setText("复制用药记录");
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            nameEditText.setEnabled(false);
            isCanClick(true);
            sureLinearLayout.setVisibility(View.VISIBLE);
        } else if ("2".equals(type)) {
            topViewManager().titleTextView().setText("用药记录详情");
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            nameEditText.setEnabled(false);
            isCanClick(false);
            sureLinearLayout.setVisibility(View.GONE);
        } else {
            topViewManager().titleTextView().setText("添加用药记录");
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
            nameEditText.setClickable(true);
            isCanClick(true);
            sureLinearLayout.setVisibility(View.VISIBLE);
        }


    }

    private void isCanClick(boolean isCanClick) {
        dosageEditText.setEnabled(isCanClick);
        timesEditText.setEnabled(isCanClick);
        specsEditText.setEnabled(isCanClick);
        startTextView.setEnabled(isCanClick);
        endTextView.setEnabled(isCanClick);
    }


    @Override
    protected void onPageLoad() {
        Call<String> requestCall = ServiceDataManager.medicineLook(pkId == null ? "" : pkId, (call, response) -> {
            if ("0000".equals(response.code)) {
                HealthyDataAllInfo allInfo = (HealthyDataAllInfo) response.object;
                bindData(allInfo);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("saveMonitorBg", requestCall);
    }

    private void bindData(HealthyDataAllInfo allInfo) {
        nameEditText.setText(allInfo.getDrugName());
        specsEditText.setText(allInfo.getDrugDose());
        specsTextView.setText(allInfo.getDrugUnit());
        timesEditText.setText(allInfo.getDrugTimes());
        dosageEditText.setText(allInfo.getDrugDose());
        startTextView.setText(allInfo.getAddTime());
        endTextView.setText(allInfo.getEndTime());
    }

    private void initListener() {
        specsLinearLayout.setOnClickListener(this);
        dosageTextView.setOnClickListener(this);
        startTextView.setOnClickListener(this);
        endTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_service_medicine_record_add, null);
        nameEditText = view.findViewById(R.id.et_service_medicine_record_add_name);
        specsEditText = view.findViewById(R.id.et_service_medicine_record_add_specs);
        specsLinearLayout = view.findViewById(R.id.ll_service_medicine_record_add_specs);
        specsTextView = view.findViewById(R.id.tv_service_medicine_record_add_specs);
        timesEditText = view.findViewById(R.id.et_service_medicine_record_add_times);
        dosageEditText = view.findViewById(R.id.et_service_medicine_record_add_dosage);
        dosageTextView = view.findViewById(R.id.tv_service_medicine_record_add_dosage);
        startTextView = view.findViewById(R.id.tv_service_medicine_record_add_start);
        endTextView = view.findViewById(R.id.tv_service_medicine_record_add_end);
        sureLinearLayout = view.findViewById(R.id.ll_service_medicine_record_add_sure);

        EditTextUtils.decimalNumber(dosageEditText, 1);
        containerView().addView(view);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_service_medicine_record_add_specs:
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
                            endTime = object.toString();
                            endTextView.setText(object.toString());
                        } else {
                            ToastUtils.getInstance().showToast(getPageContext(), "结束时间不能大于开始时间");
                        }
                    }
                });
                break;
            case R.id.ll_service_medicine_record_add_sure:
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

        Call<String> requestCall = ServiceDataManager.medicineAdd(UserInfoUtils.getArchivesId(getPageContext()), "", "2", medicineName, medicineTimes, medicineDosage, specsTextView.getText().toString().trim(), startTime, endTime, (call, response) -> {
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
        } else {
            chooseTypeList.add("mg");
            chooseTypeList.add("g");
            chooseTypeList.add("iu");
            chooseTypeList.add("ml");
            chooseTypeList.add("ug");
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
