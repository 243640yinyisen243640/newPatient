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
    private HHAtMostGridView mostGridView;
    private TextView timeTv;
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
    }

    private void initListener() {
        tangCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                gaoCb.setChecked(false);
                setVisibility(View.GONE);
            } else {
                gaoCb.setChecked(true);
                setVisibility(View.VISIBLE);
            }
        });
        gaoCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                tangCb.setChecked(false);
                setVisibility(View.GONE);
            } else {
                tangCb.setChecked(true);
                setVisibility(View.VISIBLE);
            }
        });
        timeTv.setOnClickListener(v -> {
            PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                timeTv.setText(object.toString());
            });
        });
        saveTv.setOnClickListener(v -> {

        });
    }

    private void setVisibility(int visibility) {
        arrowImageView.setVisibility(visibility);
        bgLinearLayout.setVisibility(visibility);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_important, null);
        tangCb = view.findViewById(R.id.cb_user_ill_tang);
        gaoCb = view.findViewById(R.id.cb_user_ill_gao);
        arrowImageView = view.findViewById(R.id.iv_user_ill_important);
        bgLinearLayout = view.findViewById(R.id.ll_user_ill_important);
        mostGridView = view.findViewById(R.id.gv_user_ill_important);
        timeTv = view.findViewById(R.id.tv_user_ill_important_time);
        saveTv = view.findViewById(R.id.tv_user_ill_important_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
