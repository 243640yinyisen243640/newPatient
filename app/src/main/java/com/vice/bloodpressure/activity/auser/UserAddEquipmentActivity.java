package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
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
 * 传参:
 * 描述:手动添加设备
 */
public class UserAddEquipmentActivity extends UIBaseActivity implements View.OnClickListener {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("手动添加");
        initView();
        initListener();
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
