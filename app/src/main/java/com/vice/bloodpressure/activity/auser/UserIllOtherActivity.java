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
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:isAdd 1：添加  2：编辑
 * 描述:其他诊断
 */
public class UserIllOtherActivity extends UIBaseLoadActivity {
    /**
     * 1：添加  2：编辑
     */
    private String isAdd;

    private ImageView arrowImageView;
    private LinearLayout bgLinearLayout;
    private FlexboxLayout typeFl;
    private HHAtMostGridView typeGridView;
    private HHAtMostGridView levelGridView;
    private TextView timeTv;
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
        List<EducationQuestionInvestigateModel> list = new ArrayList();
        list.add(new EducationQuestionInvestigateModel("糖尿病", "1", false));
        list.add(new EducationQuestionInvestigateModel("高血压", "2", false));
        list.add(new EducationQuestionInvestigateModel("冠心病", "3", false));
        list.add(new EducationQuestionInvestigateModel("脑卒中", "4", false));
        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", "5", false));
        list.add(new EducationQuestionInvestigateModel("糖尿病前期", "6", false));

        list.get(0).setCheck(true);
        for (int i = 0; i < list.size(); i++) {
            FlexboxLayout.LayoutParams lp = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
            textView.setPadding(DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f), DensityUtils.dip2px(getPageContext(), 15f), DensityUtils.dip2px(getPageContext(), 8f));

            textView.setTag(list.get(i).getId());
            typeFl.addView(textView, lp);
            textView.setOnClickListener(v -> {
                if ("2".equals(v.getTag())) {
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


        List<BaseLocalDataInfo> diseaseList = new ArrayList<>();
        BaseLocalDataInfo typeInfo1 = new BaseLocalDataInfo("1级高血压", "1");
        diseaseList.add(typeInfo1);
        BaseLocalDataInfo typeInfo2 = new BaseLocalDataInfo("2级高血压", "2");
        diseaseList.add(typeInfo2);
        BaseLocalDataInfo typeInfo3 = new BaseLocalDataInfo("3级高血压", "3");
        diseaseList.add(typeInfo3);

        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        typeGridView.setAdapter(adapter);

        List<BaseLocalDataInfo> levelList = new ArrayList<>();
        BaseLocalDataInfo levelInfo1 = new BaseLocalDataInfo("低危", "1");
        levelList.add(levelInfo1);
        BaseLocalDataInfo levelInfo2 = new BaseLocalDataInfo("中危", "2");
        levelList.add(levelInfo2);
        BaseLocalDataInfo levelInfo3 = new BaseLocalDataInfo("高危", "3");
        levelList.add(levelInfo3);
        BaseLocalDataInfo levelInfo4 = new BaseLocalDataInfo("很高危", "4");
        levelList.add(levelInfo4);

        PerfectDiseaseAdapter levelAdapter = new PerfectDiseaseAdapter(getPageContext(), levelList);
        levelGridView.setAdapter(levelAdapter);
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
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_other, null);
        arrowImageView = view.findViewById(R.id.iv_user_ill_other);
        bgLinearLayout = view.findViewById(R.id.ll_user_ill_other);
        typeFl = view.findViewById(R.id.fl_user_ill_other);
        typeGridView = view.findViewById(R.id.gv_user_ill_other_type);
        levelGridView = view.findViewById(R.id.gv_user_ill_other_level);
        timeTv = view.findViewById(R.id.tv_user_ill_other);
        saveTv = view.findViewById(R.id.tv_user_ill_other_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
