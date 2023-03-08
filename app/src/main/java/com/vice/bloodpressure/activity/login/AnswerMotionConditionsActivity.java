package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private ProgressBar progressBar;

    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));
        position = getIntent().getIntExtra("position", 0);
        Log.i("yys", "position===" + position);
        initView();
        initValues();
        initListener();

    }

    private void initValues() {
        if (position == 0) {
            progressBar.setProgress(10);
            progressBar.setMax(12);
        } else if (position == 1) {
            progressBar.setProgress(8);
            progressBar.setMax(10);
        } else if (position == 5) {
            progressBar.setProgress(6);
            progressBar.setMax(8);
        } else {
            progressBar.setProgress(7);
            progressBar.setMax(9);
        }
    }

    private void initListener() {
        unFitTv.setOnClickListener(this);
        fitTv.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_motion_conditions, null);
        unFitTv = view.findViewById(R.id.tv_answer_motion_back);
        fitTv = view.findViewById(R.id.tv_answer_motion_next);
        progressBar = view.findViewById(R.id.pb_answer_motion);

        containerView().addView(view);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_motion_back:
                startActivity(new Intent(getPageContext(), MainActivity.class));
                break;
            case R.id.tv_answer_motion_next:

                Intent intent = new Intent(getPageContext(), AnswerExerciseHabitActivity.class);
                intent.putExtra("position", position);
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
