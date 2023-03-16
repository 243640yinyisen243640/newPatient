package com.vice.bloodpressure.activity.auser;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.flexbox.FlexboxLayout;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.LoadStatus;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;

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
    private FlexboxLayout typeFl;
    private TextView timeTv;
    private TextView saveTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        list.add(new EducationQuestionInvestigateModel("1型糖尿病","1", false));
        list.add(new EducationQuestionInvestigateModel("2型糖尿病","2", false));
        list.add(new EducationQuestionInvestigateModel("妊娠糖尿病","3", false));

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
            textView.setMaxEms(6);
            textView.setPadding(DensityUtils.dip2px(getPageContext(), 15f), 0, DensityUtils.dip2px(getPageContext(), 15f), 0);

            textView.setTag(list.get(i).getId());
            typeFl.addView(textView, lp);
            textView.setOnClickListener(v -> {
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
    }

    private void initListener() {
        tangCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (gaoCb.isChecked()) {
                tangCb.setChecked(false);
            } else {
                tangCb.setChecked(true);
            }
        });
        gaoCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (tangCb.isChecked()) {
                gaoCb.setChecked(false);
            } else {
                gaoCb.setChecked(true);
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

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_ill_important, null);
        tangCb = view.findViewById(R.id.cb_user_ill_tang);
        gaoCb = view.findViewById(R.id.cb_user_ill_gao);
        typeFl = view.findViewById(R.id.fl_user_ill_important_type);
        timeTv = view.findViewById(R.id.tv_user_ill_important_time);
        saveTv = view.findViewById(R.id.tv_user_ill_important_save);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }

}
