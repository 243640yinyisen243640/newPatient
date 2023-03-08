package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
 * 传参：answer  上个页面的答案列表
 * 描述: 登录答题 身高体重
 * 作者: beauty
 */
public class AnswerHeightWeightActivity extends UIBaseActivity {
    //身高
    private String height = "160";
    //体重
    private String weight = "60";


    private RulerView heightRv;
    private RulerView weightRv;

    private TextView heightResultTv;
    private TextView weightResultTv;
    private ProgressBar progressBar;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        position = getIntent().getIntExtra("position", 0);
        Log.i("yys", "position===" + position);
        initView();
        initValue();
    }

    private void initValue() {
        if (position == 0) {
            progressBar.setProgress(6);
            progressBar.setMax(12);
        } else if (position == 1) {
            progressBar.setProgress(4);
            progressBar.setMax(10);
        } else if (position == 5) {
            progressBar.setProgress(2);
            progressBar.setMax(8);
        } else {
            progressBar.setProgress(3);
            progressBar.setMax(9);
        }
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_height_weight, null);
        progressBar = view.findViewById(R.id.pb_answer_content_progress);
        heightRv = view.findViewById(R.id.rv_answer_content_height);
        weightRv = view.findViewById(R.id.rv_answer_content_weight);
        heightResultTv = view.findViewById(R.id.tv_answer_content_height_result);
        weightResultTv = view.findViewById(R.id.tv_answer_content_weight_result);
        TextView nextStepTv = view.findViewById(R.id.tv_answer_content_hw_next);
        containerView().addView(view);

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



        //下一步
        nextStepTv.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), AnswerExerciseStrengthActivity.class);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            intent.putExtra("position", position);
            startActivity(intent);

        });

        TextView backTv = findViewById(R.id.tv_answer_content_hw_back);
        backTv.setOnClickListener(v -> finish());
    }

}
