package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：
 * 传参：
 * 描述:登录答题 运动禁忌
 * 作者: beauty
 * 创建日期: 2023/2/23 11:28
 */
public class AnswerMotionConditionsActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView unFitTv;
    private TextView fitTv;

    private String[] answer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));
        answer = getIntent().getStringArrayExtra("answer");

        initView();
        initListener();

    }

    private void initListener() {
        unFitTv.setOnClickListener(this);
        fitTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_motion_conditions, null);
        containerView().addView(view);

        unFitTv = view.findViewById(R.id.tv_answer_motion_back);
        fitTv = view.findViewById(R.id.tv_answer_motion_next);


        ProgressBar progressBar = view.findViewById(R.id.pb_answer_motion);
        progressBar.setProgress(answer.length + 7);
        progressBar.setMax(answer.length +7);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exercise_plan_un_fit:
                startActivity(new Intent(getPageContext(), MainActivity.class));
                break;
            case R.id.tv_exercise_plan_fit:

                Intent intent = new Intent(getPageContext(), AnswerExerciseHabitActivity.class);
                intent.putExtra("answer", answer);
                intent.putExtra("height", getIntent().getStringExtra("height"));
                intent.putExtra("weight", getIntent().getStringExtra("weight"));
                intent.putExtra("strength", getIntent().getStringExtra("strength"));
                intent.putExtra("chronicDisease", getIntent().getStringExtra("chronicDisease"));
                intent.putExtra("illType", getIntent().getStringExtra("illType"));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
