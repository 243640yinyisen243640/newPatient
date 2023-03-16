package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.UserInfo;
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
    private HHAtMostGridView heredityGridView;
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

        List<UserInfo> diseaseList = new ArrayList<>();
        UserInfo typeInfo1 = new UserInfo("子女", "1");
        diseaseList.add(typeInfo1);
        UserInfo typeInfo2 = new UserInfo("父亲", "2");
        diseaseList.add(typeInfo2);
        UserInfo typeInfo3 = new UserInfo("母亲", "3");
        diseaseList.add(typeInfo3);
        UserInfo typeInfo4 = new UserInfo("兄弟姐妹", "4");
        diseaseList.add(typeInfo4);

        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        relationshipGridView.setAdapter(adapter);

        List<UserInfo> levelList = new ArrayList<>();
        UserInfo levelInfo1 = new UserInfo("是", "0");
        levelList.add(levelInfo1);
        UserInfo levelInfo2 = new UserInfo("否", "1");
        levelList.add(levelInfo2);


        PerfectDiseaseAdapter levelAdapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        heredityGridView.setAdapter(levelAdapter);
    }

    private void initListener() {

        saveTv.setOnClickListener(v -> {

        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_family_history, null);
        relationshipGridView = view.findViewById(R.id.gv_user_ill_family_history_relationship);
        heredityGridView = view.findViewById(R.id.gv_user_ill_family_history);
        saveTv = view.findViewById(R.id.tv_user_ill_family_history_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
