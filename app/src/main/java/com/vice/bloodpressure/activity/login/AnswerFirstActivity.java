package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.login.AnswerExerciseStrengthAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:登录答题
 */
public class AnswerFirstActivity extends UIBaseActivity {
    private AnswerExerciseStrengthAdapter adapter;
    private List<EducationQuestionInvestigateModel> list = new ArrayList<>();
    private ListView listView;
    //是否多选
    private boolean isChooseMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });
        init();
        initValues();
    }

    private void initValues() {
        //进度
        list.add(new EducationQuestionInvestigateModel("糖尿病", "1", false));
        list.add(new EducationQuestionInvestigateModel("高血压", "2", false));
        list.add(new EducationQuestionInvestigateModel("冠心病", "3", false));
        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", "4", false));
        list.add(new EducationQuestionInvestigateModel("脑卒中", "5", false));
        list.add(new EducationQuestionInvestigateModel("都没有", "0", false));

        adapter = new AnswerExerciseStrengthAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        isChooseMore = false;
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (isChooseMore) {
                if (list.get(position).getText().equals("无")) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheck(false);
                    }
                    list.get(position).setCheck(true);
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getText().equals("无")) {
                            list.get(i).setCheck(false);
                        }
                    }
                    list.get(position).setCheck(!list.get(position).isCheck());
                }
            } else {
                adapter.setClickPosition(position);
            }
//            adapter.notifyDataSetChanged();
        });
    }

    private void init() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        containerView().addView(view);

        ProgressBar progressBar = findViewById(R.id.pb_answer_content);
        TextView tvTitle = findViewById(R.id.tv_answer_content_title);
        TextView tvMoro = findViewById(R.id.tv_answer_content_more);
        listView = findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = findViewById(R.id.tv_answer_content_up);
        TextView tvNext = findViewById(R.id.tv_answer_content_next);
        progressBar.setMax(7);
        progressBar.setProgress(1);
        tvTitle.setText("您是否患有下列疾病？");
        tvMoro.setVisibility(View.GONE);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {
            Intent intent;
            switch (adapter.getClickPosition()) {
                case 0:
                    intent = new Intent(getPageContext(), AnswerFirstTypeActivity.class);
                    intent.putExtra("position", adapter.getClickPosition());
                    startActivity(intent);
                    break;
                case 1:
                case 2:
                case 3:
                    intent = new Intent(getPageContext(), AnswerfourthActivity.class);
                    intent.putExtra("position", adapter.getClickPosition());
                    startActivity(intent);
                    break;
                case 4:
                    intent = new Intent(getPageContext(), AnswerHeightWeightActivity.class);
                    intent.putExtra("position", adapter.getClickPosition());
                    startActivity(intent);
                    break;
                default:
                    break;
            }

        });
    }

}
