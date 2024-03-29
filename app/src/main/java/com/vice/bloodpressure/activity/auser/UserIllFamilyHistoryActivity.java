package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.XyAtMostGridView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:isAdd 1：添加  2：编辑
 * 描述:家族史
 */
public class UserIllFamilyHistoryActivity extends UIBaseActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private XyAtMostGridView relationshipGridView;
    private CheckBox haveCheckBox;
    private CheckBox noCheckBox;
    private TextView saveTv;

    private PerfectDiseaseAdapter PerfectDiseaseAdapter;

    private List<BaseLocalDataInfo> diseaseList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("家族史");
        initView();
        initListener();
        initValues();
    }

    private void initValues() {

        diseaseList = new ArrayList<>();
        diseaseList.add(new BaseLocalDataInfo("子女", "zc"));
        diseaseList.add(new BaseLocalDataInfo("父亲", "fu"));
        diseaseList.add(new BaseLocalDataInfo("母亲", "mu"));
        diseaseList.add(new BaseLocalDataInfo("兄弟姐妹", "xd"));

        PerfectDiseaseAdapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        relationshipGridView.setAdapter(PerfectDiseaseAdapter);

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
            sureToAddData();
        });
    }


    /**
     * 确定上传数据
     */
    private void sureToAddData() {
        Call<String> requestCall = UserDataManager.editUserFilesInfoForFamily(UserInfoUtils.getArchivesId(getPageContext()), diseaseList.get(PerfectDiseaseAdapter.getClickPosition()).getId(), haveCheckBox.isChecked() ? "99" : "1", (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                setResult(RESULT_OK);
                finish();
            }else {
                saveTv.setClickable(true);
            }
        }, (call, t) -> {
            saveTv.setClickable(true);
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editUserFilesInfoForFamily", requestCall);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_family_history, null);
        relationshipGridView = view.findViewById(R.id.gv_user_ill_family_history_relationship);
        haveCheckBox = view.findViewById(R.id.cb_files_family_have);
        noCheckBox = view.findViewById(R.id.cb_files_family_no);
        saveTv = view.findViewById(R.id.tv_user_ill_family_history_save);
        containerView().addView(view);
    }


}
