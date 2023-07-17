package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
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

import java.io.Serializable;
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
    private List<Class> classList;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        init();
        initValues();
        //判断是下一题还是完成
        classList = (List<Class>) getIntent().getSerializableExtra("classList");
        index = getIntent().getIntExtra("index", 0);
        if (classList.size() == index + 1) {
            //最后一题  修改下一题为完成

        }
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
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(false);
            }
            list.get(position).setCheck(true);
            adapter.notifyDataSetChanged();

        });
        tvTitle.setText("您的糖尿病类型是什么？");
        tvMore.setVisibility(View.GONE);
        progressBar.setMax(12);
        progressBar.setProgress(3);
    }

    private void init() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMore = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = view.findViewById(R.id.tv_answer_content_up);
        TextView tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {
            //          跳转页面

            //如果不是一个大类型的最后一题
            //写自己的正常跳转逻辑

            //如果是一个大类型的最后一题
            //写下面一段代码



            Intent intent = new Intent(getPageContext(),EducationTangConcurrencyActivity.class);
            intent.putExtra("index", index);
            intent.putExtra("classList", (Serializable) classList);
            startActivity(intent);
        });
    }

}
