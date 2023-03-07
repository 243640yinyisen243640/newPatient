package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.lsp.RulerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;

import java.math.BigDecimal;

/**
 * 类名：
 * 传参：
 * 描述: 登录答题 身高体重
 * 作者: beauty
 */
public class AnswerHeightWeightActivity extends UIBaseActivity {
    //身高
    private String height = "160";
    //体重
    private String weight = "60";

    private ProgressBar progressBar;

    private RulerView heightRv;
    private RulerView weightRv;

    private TextView heightResultTv;
    private TextView weightResultTv;

    private int progressNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.activity_answer_height_weight, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        progressBar = findViewById(R.id.pb_answer_content_progress);
        heightRv = findViewById(R.id.rv_answer_content_height);
        weightRv = findViewById(R.id.rv_answer_content_weight);
        heightResultTv = findViewById(R.id.tv_answer_content_height_result);
        weightResultTv = findViewById(R.id.tv_answer_content_weight_result);
        heightRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                height = result;
                heightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                heightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });

        weightRv.setOnChooseResulterListener(new RulerView.OnChooseResulterListener() {
            @Override
            public void onEndResult(String result) {
                weight = result;
                weightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }

            @Override
            public void onScrollResult(String result) {
                weightResultTv.setText(String.valueOf(new BigDecimal(result).setScale(0, BigDecimal.ROUND_HALF_UP)));
            }
        });


        TextView nextStepTv = findViewById(R.id.tv_answer_content_hw_next);
        //下一步
        nextStepTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), AnswerExerciseStrengthActivity.class);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            startActivity(intent);
        });

        TextView backTv = findViewById(R.id.tv_answer_content_hw_back);
        backTv.setOnClickListener(v -> finish());

    }

}
