package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.login.AnswerExerciseStrengthAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:登录答题  关于运动习惯
 */
public class AnswerExerciseTimeRateActivity extends UIBaseActivity {
    private EditText timeEt;
    private ListView listView;
    private EditText rateEt;
    private TextView nextTv;
    private ProgressBar progressBar;
    private AnswerExerciseStrengthAdapter adapter;
    private List<EducationQuestionInvestigateModel> list;
    private int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));
        position = getIntent().getIntExtra("position", 0);
        Log.i("yys","position==="+position);
        initView();
        initValues();
    }

    private void initValues() {
        list = new ArrayList<>();
        list.add(new EducationQuestionInvestigateModel("是", "1", false));
        list.add(new EducationQuestionInvestigateModel("否", "0", false));
        adapter = new AnswerExerciseStrengthAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            adapter.setClickPosition(position);
        });
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
        Log.i("yys", " progressBar.setProgress==" + progressBar.getProgress());
        Log.i("yys", " progressBar.setMax==" + progressBar.getMax());
    }


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_exercise_time, null);
        listView = view.findViewById(R.id.lv_answer_exercise_time);
        timeEt = view.findViewById(R.id.et_answer_exercise_time);
        rateEt = view.findViewById(R.id.et_answer_exercise_rate);
        nextTv = view.findViewById(R.id.tv_exercise_time_next);
        TextView backTv = view.findViewById(R.id.tv_exercise_time_back);
         progressBar = view.findViewById(R.id.pb_answer_exercise_time);

        backTv.setOnClickListener(v -> finish());
        nextTv.setOnClickListener(v -> {
            setAnswerSubmit();
        });
        containerView().addView(view);
    }

    private void setAnswerSubmit() {
        String height = getIntent().getStringExtra("height");
        String weight = getIntent().getStringExtra("weight");
        String strength = getIntent().getStringExtra("strength");
        String chronicDisease = getIntent().getStringExtra("chronicDisease");
        String illType = getIntent().getStringExtra("illType");
        String isExercise = getIntent().getStringExtra("isExercise");
        String isEmpty = list.get(adapter.getClickPosition()).getId();
        ToastUtils.getInstance().showToast(getPageContext(), "答完了，进首页了");
        startActivity(new Intent(getPageContext(), MainActivity.class));
    }
}
