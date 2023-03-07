package com.vice.bloodpressure.activity.ahome.adiet;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.popwindow.DietProgrammePopupWindow;
/**
 * 类名：
 * 传参：
 * 描述: 饮食方案
 * 作者: beauty
 * 创建日期: 2023/2/28 13:11
 */
public class DietProgrammeThreeActivity extends UIBaseActivity {
    private RadioGroup radioGroup;
    private DietProgrammePopupWindow programmePopupWindow;

    //慢性病
    private String chronicDisease = "0";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.intelligence_diet_programme_three, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("饮食方案");


        radioGroup = findViewById(R.id.rg_diet_paogramme);
        TextView tvSubmit = findViewById(R.id.tv_diet_programme_three_submit);
        TextView progress = findViewById(R.id.tv_diet_programme_three_progress);
        setTextStyle(progress, 1.3f, 0, 1);

        tvSubmit.setOnClickListener(v -> {
            String height = getIntent().getStringExtra("height");
            String weight = getIntent().getStringExtra("weight");
            String workWeight = getIntent().getStringExtra("workWeight");
            //确定生成饮食方案
            Log.i("yys", "height==" + height);
            Log.i("yys", "weight==" + weight);
            Log.i("yys", "workWeight==" + workWeight);
            Log.i("yys", "chronicDisease==" + chronicDisease);
            if (programmePopupWindow == null) {
                programmePopupWindow = new DietProgrammePopupWindow(getPageContext(),
                        recommendView -> {
                            //智能推荐
                            Log.i("yys", "智能推荐");
                        },
                        chooseView -> {
                            //我自己选
                            Log.i("yys", "我自己选");
                            Intent intent = new Intent(getPageContext(),DietProgrammeChooseActivity.class);
                            startActivity(intent);
                        });
            }
            programmePopupWindow.showAsDropDown(containerView(), 0, 0, Gravity.CENTER);
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                RadioButton allView = (RadioButton) radioGroup.getChildAt(i);
                if (allView.isChecked()) {
                    chronicDisease = i+"";
                    allView.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
                } else {
                    allView.getPaint().setTypeface(Typeface.DEFAULT);
                }
            }
        });
    }
    private void setTextStyle(TextView textView, float proportion, int start, int end) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(spannableStringBuilder);
    }
}
