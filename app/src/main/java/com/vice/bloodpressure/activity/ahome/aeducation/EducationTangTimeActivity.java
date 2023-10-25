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
import com.vice.bloodpressure.model.EducationAnswerInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: beauty
 * 类名:糖尿病时间
 * 传参:
 * 描述:
 */
public class EducationTangTimeActivity extends UIBaseActivity {
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();
    private ListView listView;

    private ProgressBar progressBar;
    private TextView tvTitle;
    private TextView tvMoro;
    private TextView tvNext;

    private List<Class> classList;
    private int index;
    private EducationAnswerInfo answerInfo;
    private int allPage;
    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        //判断是下一题还是完成
        classList = (List<Class>) getIntent().getSerializableExtra("classList");
        index = getIntent().getIntExtra("index", 0);
        answerInfo = (EducationAnswerInfo) getIntent().getSerializableExtra("answerInfo");
        allPage = getIntent().getIntExtra("allPage", 0);
        page = getIntent().getIntExtra("page", 0);

        init();
        initValues();

    }

    private void initValues() {
        if (classList.size() == index + 1) {
            //最后一题  修改下一题为完成
            tvNext.setText("完成");
        }
        //进度
        tvTitle.setText("您患糖尿病有多长时间了？");
        tvMoro.setVisibility(View.GONE);
        progressBar.setMax(allPage);
        progressBar.setProgress(page);

        list.add(new BaseLocalDataInfo("小于1年", "1"));
        list.add(new BaseLocalDataInfo("1~5年", "2"));
        list.add(new BaseLocalDataInfo("大于5年", "3"));
        list.get(0).setCheck(true);
        answerInfo.setDmTime("1");
        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setCheck(false);
            }
            list.get(position).setCheck(true);
            answerInfo.setDmBasics(list.get(position).getId());
            adapter.notifyDataSetChanged();

        });

    }

    private void init() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMoro = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        TextView tvUp = view.findViewById(R.id.tv_answer_content_up);
        tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

        tvUp.setOnClickListener(v -> finish());
        tvNext.setOnClickListener(v -> {
            //          跳转页面
            if (classList.size() > index + 1) {
                //有下一题
                Intent intent = new Intent(this, classList.get(index + 1));
                intent.putExtra("index", index + 1);
                intent.putExtra("classList", (Serializable) classList);
                intent.putExtra("answerInfo", answerInfo);
                intent.putExtra("page", page + 1);
                intent.putExtra("allPage", allPage);
                //其他的你自己传
                startActivity(intent);
            } else {
                //最后一题

            }
        });
    }

}