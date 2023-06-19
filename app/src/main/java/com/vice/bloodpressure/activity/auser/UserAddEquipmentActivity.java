package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.EquipmetInfo;
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
 * 传参: type  1 手动添加  2扫一扫
 * 描述:手动添加设备
 */
public class UserAddEquipmentActivity extends UIBaseLoadActivity implements View.OnClickListener {
    private TextView typeTextView;
    private TextView nameTextView;
    private EditText numEditText;
    private LinearLayout sureLinearLayout;


    private List<EquipmetInfo> equipList;
    /**
     * 设备类型
     */
    private String equipmentType = "1";
    /**
     * 设备id
     */
    private String equipmentID = "-1";
    /**
     * 设备号
     */
    private String deviceCode = "";
    /**
     * 1 手动添加  2扫一扫
     */
    private String type = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("手动添加");
        deviceCode = getIntent().getStringExtra("deviceCode");
        type = getIntent().getStringExtra("type");
        initView();
        initListener();
        if ("2".equals(type)) {
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        } else {
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        }
    }

    @Override
    protected void onPageLoad() {
        Call<String> requestCall = UserDataManager.getScanInfo(deviceCode, (call, response) -> {
            if ("0000".equals(response.code)) {
                loadViewManager().changeLoadState(LoadStatus.SUCCESS);
                EquipmetInfo equipmetInfo = (EquipmetInfo) response.object;
                bindData(equipmetInfo);
            } else {
                loadViewManager().changeLoadState(LoadStatus.FAILED);
            }
        }, (call, t) -> {
            loadViewManager().changeLoadState(LoadStatus.FAILED);
        });
        addRequestCallToMap("getScanInfo", requestCall);
    }

    private void bindData(EquipmetInfo equipmetInfo) {
        equipmentType = equipmetInfo.getDeviceCategory();
        if ("1".equals(equipmetInfo.getDeviceCategory())) {
            typeTextView.setText("血糖仪");
        } else {
            typeTextView.setText("血压计");
        }

        nameTextView.setText(equipmetInfo.getBrandName());
        equipmentID = equipmetInfo.getBrandId();
        numEditText.setText(deviceCode);
        typeTextView.setClickable(false);
        nameTextView.setClickable(false);
        numEditText.setClickable(false);
        numEditText.setEnabled(false);
    }

    private void initListener() {
        typeTextView.setOnClickListener(this);
        nameTextView.setOnClickListener(this);
        sureLinearLayout.setOnClickListener(this);
    }

    private void getEquipmentList() {
        Call<String> requestCall = UserDataManager.getEquipmentNameList((call, response) -> {
            if ("0000".equals(response.code)) {
                equipList = (List<EquipmetInfo>) response.object;
                List<String> list = new ArrayList<>();
                if (equipList != null && equipList.size() > 0) {
                    for (int i = 0; i < equipList.size(); i++) {
                        String typeName = equipList.get(i).getBrandName();
                        list.add(typeName);
                    }
                }
                chooseTypeWindow(list);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("getEquipmentNameList", requestCall);
    }

    /**
     * 选择品牌名称
     */
    private void chooseTypeWindow(List<String> stringList) {
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "选择名称", stringList, object -> {
            equipmentID = equipList.get(Integer.parseInt(String.valueOf(object))).getId();
            nameTextView.setText(equipList.get(Integer.parseInt(String.valueOf(object))).getBrandName());
        });
    }

    /**
     * 确认解绑
     */
    private void sureToAdd() {
        if ("-1".equals(equipmentID)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择设备品牌");
            return;
        }
        String deviceCode = numEditText.getText().toString().trim();
        if (TextUtils.isEmpty(deviceCode)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入设备号");
            return;
        }


        Call<String> requestCall = UserDataManager.userBindDevice(equipmentID, equipmentType, deviceCode, UserInfoUtils.getArchivesId(getPageContext()), (call, response) ->
        {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userBindDevice", requestCall);
    }

    private void choose() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("血糖仪");
        exerciseList.add("血压计");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "设备类型", exerciseList, object -> {
                    typeTextView.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    equipmentType = exerciseList.get(Integer.parseInt(String.valueOf(object))) + 1 + "";
                }
        );
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_add_equipment, null);
        typeTextView = view.findViewById(R.id.tv_add_equipment_type);
        nameTextView = view.findViewById(R.id.tv_add_equipment_name);
        numEditText = view.findViewById(R.id.et_add_equipment_num);
        sureLinearLayout = view.findViewById(R.id.ll_add_equipment_sure);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_equipment_type:
                choose();
                break;
            case R.id.tv_add_equipment_name:
                getEquipmentList();
                break;
            case R.id.ll_add_equipment_sure:
                sureToAdd();
                break;
            default:
                break;
        }
    }
}
