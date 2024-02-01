package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：
 * 传参：
 * 描述: 问卷调查
 * 作者: beauty
 * 创建日期: 2023/3/1 11:11
 */
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
//            Intent intent = new Intent(getPageContext(), EducationQuestionInvestigateActivity.class);
//            intent.putExtra("questionNowNum", 1);
//            startActivity(intent);
            Log.i("yys","2");
            Intent intent = new Intent(getPageContext(), EducationHeightWeightActivity.class);
            startActivity(intent);
        });
    }
}
