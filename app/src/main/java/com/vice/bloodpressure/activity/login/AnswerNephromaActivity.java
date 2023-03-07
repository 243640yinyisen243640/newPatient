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
    private ProgressBar progressBar;

    //慢性病
    private String chronicDisease = "0";
    private String[] answer;
    private String height;
    private String weight;
    private String strength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().moreTextView().setOnClickListener(v -> startActivity(new Intent(getPageContext(), MainActivity.class)));


        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        answer = getIntent().getStringArrayExtra("answer");
        initView();


    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_nephroma, null);
        containerView().addView(view);
        radioGroup = findViewById(R.id.rg_diet_paogramme);
        TextView tvBack = findViewById(R.id.tv_answer_nephroma_back);
        TextView tvSubmit = findViewById(R.id.tv_answer_nephroma_next);
        progressBar = findViewById(R.id.pb_answer_nephroma);
        progressBar.setProgress(answer.length + 7);
        progressBar.setMax(answer.length +7);
        tvBack.setOnClickListener(v -> finish());
        tvSubmit.setOnClickListener(v -> {

            //确定生成饮食方案
            Log.i("yys", "height==" + height);
            Log.i("yys", "weight==" + weight);
            Log.i("yys", "chronicDisease==" + chronicDisease);
            Intent intent = new Intent(getPageContext(), AnswerIllnessActivity.class);
            intent.putExtra("answer", answer);
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
