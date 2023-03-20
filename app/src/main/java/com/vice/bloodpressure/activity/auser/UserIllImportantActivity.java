package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:isAdd 1：添加  2：编辑
 * 描述:主要诊断
 */
public class UserIllImportantActivity extends UIBaseLoadActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private CheckBox tangCb;
    private CheckBox gaoCb;
    private ImageView arrowImageView;
    private LinearLayout bgLinearLayout;
    private LinearLayout gaoLinearLayout;
    private HHAtMostGridView mostGridView;
    private HHAtMostGridView gaoMostGridView;
    private HHAtMostGridView levelMostGridView;
    private TextView timeTv;
    private TextView gaoTimeTv;
    private TextView saveTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("主要诊断");
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
        List<UserInfo> list = new ArrayList();
        list.add(new UserInfo("1型糖尿病", "1"));
        list.add(new UserInfo("2型糖尿病", "2"));
        list.add(new UserInfo("妊娠糖尿病", "3"));

        PerfectDiseaseAdapter levelAdapter = new PerfectDiseaseAdapter(getPageContext(), list);
        mostGridView.setAdapter(levelAdapter);

        List<UserInfo> diseaseList = new ArrayList<>();
        UserInfo typeInfo1 = new UserInfo("1级高血压", "1");
        diseaseList.add(typeInfo1);
        UserInfo typeInfo2 = new UserInfo("2级高血压", "2");
        diseaseList.add(typeInfo2);
        UserInfo typeInfo3 = new UserInfo("3级高血压", "3");
        diseaseList.add(typeInfo3);

        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        gaoMostGridView.setAdapter(adapter);

        List<UserInfo> levelList = new ArrayList<>();
        UserInfo levelInfo1 = new UserInfo("低危", "1");
        levelList.add(levelInfo1);
        UserInfo levelInfo2 = new UserInfo("中危", "2");
        levelList.add(levelInfo2);
        UserInfo levelInfo3 = new UserInfo("高危", "3");
        levelList.add(levelInfo3);
        UserInfo levelInfo4 = new UserInfo("很高危", "4");
        levelList.add(levelInfo4);

        PerfectDiseaseAdapter gaoLevelAdapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        levelMostGridView.setAdapter(gaoLevelAdapter);
    }

    private void initListener() {
        tangCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bgLinearLayout.setVisibility(View.VISIBLE);
                gaoLinearLayout.setVisibility(View.GONE);
                gaoCb.setChecked(false);
            } else {
                bgLinearLayout.setVisibility(View.GONE);
                gaoLinearLayout.setVisibility(View.VISIBLE);

                gaoCb.setChecked(true);
            }
        });
        gaoCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bgLinearLayout.setVisibility(View.GONE);
                gaoLinearLayout.setVisibility(View.VISIBLE);
                tangCb.setChecked(false);
            } else {
                bgLinearLayout.setVisibility(View.VISIBLE);
                gaoLinearLayout.setVisibility(View.GONE);
                tangCb.setChecked(true);
            }
        });
        timeTv.setOnClickListener(v -> {
          showTime("1");
        });
        gaoTimeTv.setOnClickListener(v -> {
            showTime("2");
        });
        saveTv.setOnClickListener(v -> {

        });
    }

    /**
     *
     * @param type 1:血糖 2：血压
     */
    private void showTime(String type){
        PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
            if ("1".equals(type)){
                timeTv.setText(object.toString());
            }else {
                gaoTimeTv.setText(object.toString());
            }
        });
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_important, null);
        tangCb = view.findViewById(R.id.cb_user_ill_tang);
        gaoCb = view.findViewById(R.id.cb_user_ill_gao);
        arrowImageView = view.findViewById(R.id.iv_user_ill_important);
        bgLinearLayout = view.findViewById(R.id.ll_user_ill_important);
        gaoLinearLayout = view.findViewById(R.id.ll_user_ill_important_gao);
        mostGridView = view.findViewById(R.id.gv_user_ill_important);
        gaoMostGridView = view.findViewById(R.id.gv_user_ill_important_gao_type);
        levelMostGridView = view.findViewById(R.id.gv_user_ill_important_gao_level);
        gaoTimeTv = view.findViewById(R.id.tv_user_ill_important_time);
        timeTv = view.findViewById(R.id.tv_user_ill_important_gao_time);
        saveTv = view.findViewById(R.id.tv_user_ill_important_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
