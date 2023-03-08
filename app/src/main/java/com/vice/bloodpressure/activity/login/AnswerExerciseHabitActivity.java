package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.login.AnswerExerciseStrengthAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:height 身高  weight体重  answer 前面的答案列表
 * 描述:登录答题 运动习惯
 */
public class AnswerExerciseHabitActivity extends UIBaseActivity {

    private int position;

    private AnswerExerciseStrengthAdapter adapter;
    private List<EducationQuestionInvestigateModel> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        position = getIntent().getIntExtra("position", 0);
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        Log.i("yys","position==="+position);
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_exercise_strength, null);
        ListView listView = view.findViewById(R.id.lv_answer_content_strength);
        TextView backTextView = view.findViewById(R.id.tv_answer_content_strength_back);
        TextView nextTextView = view.findViewById(R.id.tv_answer_content_strength_next);
        TextView titleTextView = view.findViewById(R.id.tv_answer_content_strength_title);
        titleTextView.setText("是否有运动习惯？");
        ProgressBar progressBar = view.findViewById(R.id.pb_answer_strength);
        if (position == 0) {
            progressBar.setProgress(11);
            progressBar.setMax(12);
        } else if (position == 1) {
            progressBar.setProgress(8);
            progressBar.setMax(10);
        } else if (position == 5) {
            progressBar.setProgress(7);
            progressBar.setMax(8);
        } else {
            progressBar.setProgress(8);
            progressBar.setMax(9);
        }
        containerView().addView(view);
        backTextView.setOnClickListener(v -> finish());
        nextTextView.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), AnswerExerciseTimeRateActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("height", getIntent().getStringExtra("height"));
            intent.putExtra("weight", getIntent().getStringExtra("weight"));
            intent.putExtra("strength", getIntent().getStringExtra("strength"));
            intent.putExtra("chronicDisease", getIntent().getStringExtra("chronicDisease"));
            intent.putExtra("illType", getIntent().getStringExtra("illType"));
            intent.putExtra("isExercise", list.get(adapter.getClickPosition()).getId());
            startActivity(intent);
        });
        list = new ArrayList<>();
        list.add(new EducationQuestionInvestigateModel("是", "1", false));
        list.add(new EducationQuestionInvestigateModel("否", "0", false));
        adapter = new AnswerExerciseStrengthAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            adapter.setClickPosition(position);
        });
    }
}
