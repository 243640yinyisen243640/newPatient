package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class AnswerExerciseStrengthActivity extends UIBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_exercise_strength, null);
        ListView listView = view.findViewById(R.id.lv_answer_content_strength);
        TextView backTextView = view.findViewById(R.id.tv_answer_content_strength_back);
        TextView nextTextView = view.findViewById(R.id.tv_answer_content_strength_next);
        containerView().addView(view);
        backTextView.setOnClickListener(v -> finish());
        nextTextView.setOnClickListener(v -> {
        });
        List<EducationQuestionInvestigateModel> list = new ArrayList<>();
        list.add(new EducationQuestionInvestigateModel("轻体力劳动", false));
        list.add(new EducationQuestionInvestigateModel("中体力劳动", false));
        list.add(new EducationQuestionInvestigateModel("重体力劳动", false));
        list.add(new EducationQuestionInvestigateModel("卧床", false));
        EducationQuestionInvestigateAdapter adapter = new EducationQuestionInvestigateAdapter(list, getPageContext());
        listView.setAdapter(adapter);
    }
}
