package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class UserPayStyleActivity extends UIBaseActivity {
    private ListView listView;
    private EducationQuestionInvestigateAdapter adapter;
    private List<EducationQuestionInvestigateModel> list = new ArrayList<>();

    private LinearLayout sureLinearLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intView();
        initValues();
        topViewManager().titleTextView().setText("医疗支付方式");
    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_pay_style, null);
        listView = view.findViewById(R.id.lv_user_files_investigate);
        sureLinearLayout = view.findViewById(R.id.ll_user_files_sure);
        containerView().addView(view);
        sureLinearLayout.setOnClickListener(v -> {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCheck()) {
                    builder.append(list.get(i).getId());
                    builder.append(",");
                }
            }
            builder.deleteCharAt(builder.length() - 1);
            if (builder.length() == 0) {
                ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                return;
            }
        });
    }

    private void initValues() {
        list.add(new EducationQuestionInvestigateModel("社会医疗保险", "1", false));
        list.add(new EducationQuestionInvestigateModel("新型农村医疗合作保险", "2", false));
        list.add(new EducationQuestionInvestigateModel("商业保险", "3", false));
        list.add(new EducationQuestionInvestigateModel("城镇居民医疗保险", "4", false));
        list.add(new EducationQuestionInvestigateModel("公费医疗", "5", false));
        list.add(new EducationQuestionInvestigateModel("自费医疗", "6", false));
        list.add(new EducationQuestionInvestigateModel("其他险", "7", false));

        adapter = new EducationQuestionInvestigateAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).getText().equals("其他险")) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getText().equals("其他险")) {
                        list.get(i).setCheck(false);
                    }
                }
                list.get(position).setCheck(!list.get(position).isCheck());
            }
            adapter.notifyDataSetChanged();
        });
    }

}
