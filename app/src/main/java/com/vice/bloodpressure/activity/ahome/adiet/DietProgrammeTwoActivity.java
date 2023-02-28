package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 类名：
 * 传参：
 * 描述: 饮食方案
 * 作者: beauty
 * 创建日期: 2023/2/28 13:11
 */

public class DietProgrammeTwoActivity extends UIBaseActivity implements View.OnClickListener {
    private ImageView ivLight;
    private ImageView ivMiddle;
    private ImageView ivHeavy;
    private ImageView ivBedridden;

    private String workWeight = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getPageContext(), R.layout.intelligence_diet_programme_two, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("饮食方案");

        LinearLayout llLight = findViewById(R.id.ll_diet_programme_two_light);
        llLight.setOnClickListener(this);
        LinearLayout llMiddle = findViewById(R.id.ll_diet_programme_two_middle);
        llMiddle.setOnClickListener(this);
        LinearLayout llHeavy = findViewById(R.id.ll_diet_programme_two_heavy);
        llHeavy.setOnClickListener(this);
        LinearLayout llBedridden = findViewById(R.id.ll_diet_programme_two_bedridden);
        llBedridden.setOnClickListener(this);
        TextView tvUpStep = findViewById(R.id.tv_diet_programme_two_up_step);
        tvUpStep.setOnClickListener(this);
        TextView tvNextStep = findViewById(R.id.tv_diet_programme_two_next_step);
        tvNextStep.setOnClickListener(this);
        ivLight = findViewById(R.id.iv_diet_programme_two_light);
        ivMiddle = findViewById(R.id.iv_diet_programme_two_middle);
        ivHeavy = findViewById(R.id.iv_diet_programme_two_heavy);
        ivBedridden = findViewById(R.id.iv_diet_programme_two_bedridden);

        TextView progress = findViewById(R.id.tv_diet_programme_two_progress);
        setTextStyle(progress, 1.3f, 0, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_diet_programme_two_light:
                //轻体力劳动者
                checkWork("0");
                break;
            case R.id.ll_diet_programme_two_middle:
                //中体力劳动者
                checkWork("1");
                break;
            case R.id.ll_diet_programme_two_heavy:
                //重体力劳动者
                checkWork("2");
                break;
            case R.id.ll_diet_programme_two_bedridden:
                //卧床
                checkWork("3");
                break;
            case R.id.tv_diet_programme_two_up_step:
                //上一步
                finish();
                break;
            case R.id.tv_diet_programme_two_next_step:
                //下一步
                Intent intent = new Intent(getPageContext(), DietProgrammeThreeActivity.class);
                intent.putExtra("workWeight", workWeight);
                intent.putExtra("height", getIntent().getStringExtra("height"));
                intent.putExtra("weight", getIntent().getStringExtra("weight"));
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void checkWork(String workWeight) {
        this.workWeight = workWeight;
        ivLight.setImageResource(R.drawable.circle_uncheck);
        ivMiddle.setImageResource(R.drawable.circle_uncheck);
        ivHeavy.setImageResource(R.drawable.circle_uncheck);
        ivBedridden.setImageResource(R.drawable.circle_uncheck);
        switch (workWeight) {
            case "0":
                ivLight.setImageResource(R.drawable.circle_check);
                ivMiddle.setImageResource(R.drawable.circle_uncheck);
                ivHeavy.setImageResource(R.drawable.circle_uncheck);
                ivBedridden.setImageResource(R.drawable.circle_uncheck);
                break;
            case "1":
                ivLight.setImageResource(R.drawable.circle_uncheck);
                ivMiddle.setImageResource(R.drawable.circle_check);
                ivHeavy.setImageResource(R.drawable.circle_uncheck);
                ivBedridden.setImageResource(R.drawable.circle_uncheck);
                break;
            case "2":
                ivLight.setImageResource(R.drawable.circle_uncheck);
                ivMiddle.setImageResource(R.drawable.circle_uncheck);
                ivHeavy.setImageResource(R.drawable.circle_check);
                ivBedridden.setImageResource(R.drawable.circle_uncheck);
                break;
            case "3":
                ivLight.setImageResource(R.drawable.circle_uncheck);
                ivMiddle.setImageResource(R.drawable.circle_uncheck);
                ivHeavy.setImageResource(R.drawable.circle_uncheck);
                ivBedridden.setImageResource(R.drawable.circle_check);
                break;
            default:
                break;
        }
    }

    private void setTextStyle(TextView textView, float proportion, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }
}
