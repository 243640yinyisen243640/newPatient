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
public class AnswerFirstTypeActivity extends UIBaseActivity {
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
        list.add(new EducationQuestionInvestigateModel("1型糖尿病", "1", false));
        list.add(new EducationQuestionInvestigateModel("2型糖尿病", "2", false));
        list.add(new EducationQuestionInvestigateModel("妊娠糖尿病", "3", false));
        list.add(new EducationQuestionInvestigateModel("其他", "0", false));

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

        ProgressBar progressBar = findViewById(R.id.pb_answer_content);
        TextView tvTitle = view.findViewById(R.id.tv_answer_content_title);
        TextView tvMoro = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = view.findViewById(R.id.tv_answer_content_up);
        TextView tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

        progressBar.setMax(12);
        progressBar.setProgress(2);
        tvTitle.setText("您的糖尿病类型是什么？");
        tvMoro.setVisibility(View.GONE);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {
            Intent intent = new Intent(getPageContext(), AnswerSecondActivity.class);
            intent.putExtra("position", adapter.getClickPosition());
            startActivity(intent);
        });
    }

}
