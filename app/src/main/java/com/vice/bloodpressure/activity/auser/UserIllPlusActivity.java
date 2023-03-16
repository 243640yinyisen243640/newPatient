package com.vice.bloodpressure.activity.auser;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.flexbox.FlexboxLayout;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.DensityUtils;
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
public class UserIllPlusActivity extends UIBaseLoadActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private ImageView arrowImageView;
    private LinearLayout bgLinearLayout;
    private FlexboxLayout typeFl;
    private HHAtMostGridView gridView;
    private TextView timeTv;
    private TextView saveTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("合并症");
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
        List<EducationQuestionInvestigateModel> list = new ArrayList();
        list.add(new EducationQuestionInvestigateModel("糖尿病肾病", "1", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病神经病变", "2", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病下肢血管病变", "3", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病视网膜病变", "4", false));

        list.get(0).setCheck(true);
        for (int i = 0; i < list.size(); i++) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, DensityUtils.dip2px(getPageContext(), 28f));
            lp.setMargins(0, DensityUtils.dip2px(getPageContext(), 10f), DensityUtils.dip2px(getPageContext(), 10f), 0);
            TextView textView = new TextView(getPageContext());
            textView.setTextSize(15f);
            if (list.get(i).isCheck()) {
                textView.setBackgroundResource(R.drawable.shape_bg_main_gra_90);
                textView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                textView.setBackgroundResource(R.drawable.shape_bg_white_black_90_1);
                textView.setTextColor(Color.parseColor("#242424"));
            }

            textView.setGravity(Gravity.CENTER);
            textView.setText(list.get(i).getText());
            textView.setMaxLines(1);
            textView.setPadding(DensityUtils.dip2px(getPageContext(), 15f), 0, DensityUtils.dip2px(getPageContext(), 15f), 0);

            textView.setTag(list.get(i).getId());
            typeFl.addView(textView, lp);
            textView.setOnClickListener(v -> {
                if ("1".equals(v.getTag())) {
                    arrowImageView.setVisibility(View.VISIBLE);
                    bgLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    arrowImageView.setVisibility(View.GONE);
                    bgLinearLayout.setVisibility(View.GONE);
                }
                for (int j = 0; j < list.size(); j++) {

                    if (v.getTag().equals(list.get(j).getId())) {
                        //设置选中
                        list.get(j).setCheck(true);
                        typeFl.getChildAt(j).setBackgroundResource(R.drawable.shape_bg_main_gra_90);
                        ((TextView) typeFl.getChildAt(j)).setTextColor(Color.parseColor("#FFFFFF"));
                    } else {
                        //设置不选中
                        list.get(j).setCheck(false);
                        typeFl.getChildAt(j).setBackgroundResource(R.drawable.shape_bg_white_black_90_1);
                        ((TextView) typeFl.getChildAt(j)).setTextColor(Color.parseColor("#242424"));
                    }
                }
            });
        }


        List<UserInfo> diseaseList = new ArrayList<>();
        UserInfo typeInfo1 = new UserInfo("1期", "1");
        diseaseList.add(typeInfo1);
        UserInfo typeInfo2 = new UserInfo("2期", "2");
        diseaseList.add(typeInfo2);
        UserInfo typeInfo3 = new UserInfo("3a期", "3");
        diseaseList.add(typeInfo3);
        UserInfo typeInfo4 = new UserInfo("3b期", "4");
        diseaseList.add(typeInfo4);
        UserInfo typeInfo5 = new UserInfo("4期", "5");
        diseaseList.add(typeInfo5);
        UserInfo typeInfo6 = new UserInfo("5期", "6");
        diseaseList.add(typeInfo6);
        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        gridView.setAdapter(adapter);
    }

    private void initListener() {

        timeTv.setOnClickListener(v -> {
            PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, object -> {
                timeTv.setText(object.toString());
            });
        });
        saveTv.setOnClickListener(v -> {

        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_plus, null);
        arrowImageView = view.findViewById(R.id.iv_user_ill_plus);
        bgLinearLayout = view.findViewById(R.id.ll_user_ill_plus);
        typeFl = view.findViewById(R.id.fl_user_ill_plus);
        gridView = view.findViewById(R.id.gv_user_ill_plus);
        timeTv = view.findViewById(R.id.tv_user_ill_plus);
        saveTv = view.findViewById(R.id.tv_user_ill_plus_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
