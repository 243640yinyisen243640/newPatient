package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserAddEquipmentActivity extends UIBaseActivity {
    private TextView typeTextView;
    private EditText nameEditText;
    private EditText numEditText;
    private LinearLayout sureLinearLayout;

    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("手动添加");
        initView();
        initListener();
    }

    private void initListener() {
        typeTextView.setOnClickListener(v -> {
            choose();
        });
        sureLinearLayout.setOnClickListener(v -> {
        });
    }

    private void choose() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("血糖仪");
        exerciseList.add("血压计");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "设备类型", exerciseList, object -> {
                    typeTextView.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    type = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                }
        );
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_add_equipment, null);
        typeTextView = view.findViewById(R.id.tv_add_equipment_type);
        nameEditText = view.findViewById(R.id.et_add_equipment_name);
        numEditText = view.findViewById(R.id.et_add_equipment_num);
        sureLinearLayout = view.findViewById(R.id.ll_add_equipment_sure);
        containerView().addView(view);
    }
}
