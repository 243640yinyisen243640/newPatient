package com.vice.bloodpressure.activity.ahome.aeducation;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateRealAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.BaseLocalDataInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:糖尿病类型
 * 传参:
 * 描述:
 */
public class EducationAnswerTangActivity extends UIBaseActivity {
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();
    private ListView listView;
    private TextView tvTitle;
    private TextView tvMore;
    private ProgressBar progressBar;
    private TextView tvMoro;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        init();
        initValues();
    }

    private void initValues() {
        //进度
        list.add(new BaseLocalDataInfo("1型糖尿病", "1"));
        list.add(new BaseLocalDataInfo("2型糖尿病", "2"));
        list.add(new BaseLocalDataInfo("妊娠糖尿病", "3"));
        list.add(new BaseLocalDataInfo("其他", "4"));

        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            list.get(position).setCheck(!list.get(position).isCheck());
            adapter.notifyDataSetChanged();

        });
        tvTitle.setText("您的糖尿病类型是什么？");
        tvMoro.setVisibility(View.GONE);
        progressBar.setMax(12);
        progressBar.setProgress(3);
    }

    private void init() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMoro = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = view.findViewById(R.id.tv_answer_content_up);
        TextView tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {

            //          跳转页面

        });
    }

}
