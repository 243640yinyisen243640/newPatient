package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

public class EducationQuestionInvestigateBeginActivity extends UIBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.education_question_investigate_begin, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("问卷调查");

        TextView tvQuestionInvestigateBegin = findViewById(R.id.tv_education_question_investigate_begin);
        //问卷调查开始答题
        tvQuestionInvestigateBegin.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), EducationQuestionInvestigateActivity.class);
            intent.putExtra("questionNowNum", 1);
            startActivity(intent);
        });
    }
}
