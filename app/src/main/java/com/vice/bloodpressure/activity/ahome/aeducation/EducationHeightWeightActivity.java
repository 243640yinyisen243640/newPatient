package com.vice.bloodpressure.activity.ahome.aeducation;

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
 * 传参：
 * 描述:  身高体重
 * 作者: beauty
 */
public class EducationHeightWeightActivity extends UIBaseActivity {
    //身高
    private String height = "160";
    //体重
    private String weight = "60";


    private RulerView heightRv;
    private RulerView weightRv;

    private TextView heightResultTv;
    private TextView weightResultTv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        initView();
        initValue();
    }

    private void initValue() {
        progressBar.setProgress(1);
        progressBar.setMax(11 );
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
            Log.i("yys","3");
            Intent intent = new Intent(getPageContext(), EducationIllnessActivity.class);
            intent.putExtra("height", height);
            intent.putExtra("weight", weight);
            startActivity(intent);
        });
        TextView backTv = findViewById(R.id.tv_answer_content_hw_back);
        backTv.setOnClickListener(v -> finish());
    }

}
