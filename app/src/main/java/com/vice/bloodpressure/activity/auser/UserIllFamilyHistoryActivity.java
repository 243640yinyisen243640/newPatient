package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:isAdd 1：添加  2：编辑
 * 描述:家族史
 */
public class UserIllFamilyHistoryActivity extends UIBaseLoadActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private HHAtMostGridView relationshipGridView;
    private CheckBox haveCheckBox;
    private CheckBox noCheckBox;
    private TextView saveTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("其他诊断");
        isAdd = getIntent().getStringExtra("isAdd");
        if ("1".equals(isAdd)) {
            loadViewManager().changeLoadState(LoadStatus.SUCCESS);
        } else {
            loadViewManager().changeLoadState(LoadStatus.LOADING);
        }
        initView();
        initListener();
        initValues();
    }

    private void initValues() {

        List<BaseLocalDataInfo> diseaseList = new ArrayList<>();
        BaseLocalDataInfo typeInfo1 = new BaseLocalDataInfo("子女", "1");
        diseaseList.add(typeInfo1);
        BaseLocalDataInfo typeInfo2 = new BaseLocalDataInfo("父亲", "2");
        diseaseList.add(typeInfo2);
        BaseLocalDataInfo typeInfo3 = new BaseLocalDataInfo("母亲", "3");
        diseaseList.add(typeInfo3);
        BaseLocalDataInfo typeInfo4 = new BaseLocalDataInfo("兄弟姐妹", "4");
        diseaseList.add(typeInfo4);

        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        relationshipGridView.setAdapter(adapter);

    }

    private void initListener() {
        haveCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                noCheckBox.setChecked(false);

            } else {
                noCheckBox.setChecked(true);

            }
        });
        noCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                haveCheckBox.setChecked(false);
            } else {
                haveCheckBox.setChecked(true);
            }
        });
        saveTv.setOnClickListener(v -> {

        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_family_history, null);
        relationshipGridView = view.findViewById(R.id.gv_user_ill_family_history_relationship);
        haveCheckBox = view.findViewById(R.id.cb_files_family_have);
        noCheckBox = view.findViewById(R.id.cb_files_family_no);
        saveTv = view.findViewById(R.id.tv_user_ill_family_history_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
