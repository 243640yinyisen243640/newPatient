package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：
 * 传参：answer 前面几个问题的答案
 * height 身高
 * weight体重
 * strength 运动强度
 * 描述: 登录答题  肾病
 * 作者: beauty
 * 创建日期: 2023/2/28 13:11
 */
public class AnswerNephromaActivity extends UIBaseActivity {
    private RadioGroup radioGroup;

    //慢性病
    private String chronicDisease = "0";
    private int position;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        position = getIntent().getIntExtra("position", 0);
        Log.i("yys", "position===" + position);
        initView();
        initValues();
    }

    private void initValues() {
        if (position == 0) {
            progressBar.setProgress(8);
            progressBar.setMax(12);
        } else if (position == 1) {
            progressBar.setProgress(6);
            progressBar.setMax(10);
        } else if (position == 5) {
            progressBar.setProgress(4);
            progressBar.setMax(8);
        } else {
            progressBar.setProgress(5);
            progressBar.setMax(9);
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_nephroma, null);
        radioGroup = view.findViewById(R.id.rg_answer_programme);
        TextView tvBack = view.findViewById(R.id.tv_answer_nephroma_back);
        TextView tvSubmit = view.findViewById(R.id.tv_answer_nephroma_next);
        progressBar = view.findViewById(R.id.pb_answer_nephroma);

        containerView().addView(view);

        tvBack.setOnClickListener(v -> finish());
        tvSubmit.setOnClickListener(v -> {

            Log.i("yys", "chronicDisease==" + chronicDisease);
            Intent intent = new Intent(getPageContext(), AnswerIllnessActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("height", getIntent().getStringExtra("height"));
            intent.putExtra("weight", getIntent().getStringExtra("weight"));
            intent.putExtra("strength", getIntent().getStringExtra("strength"));
            intent.putExtra("chronicDisease", chronicDisease);
            startActivity(intent);

        });
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton allView = (RadioButton) radioGroup.getChildAt(i);
                if (allView.isChecked()) {
                    chronicDisease = i + "";
                    allView.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    allView.getPaint().setTypeface(Typeface.DEFAULT);
                }
            }
        });
    }

}
