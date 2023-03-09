package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class AnswerIllnessActivity extends UIBaseActivity implements View.OnClickListener {
    private ListView listView;
    private EducationQuestionInvestigateAdapter adapter;
    private List<EducationQuestionInvestigateModel> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;
    private ProgressBar progressBar;
    private int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intView();
        initValues();

        topViewManager().titleTextView().setText("个性化健康方案定制");
        topViewManager().moreTextView().setText("跳过答题");
        position = getIntent().getIntExtra("position", 0);
        Log.i("yys", "position===" + position);
        topViewManager().moreTextView().setOnClickListener(v -> {
            startActivity(new Intent(getPageContext(), MainActivity.class));
        });

        tvUp.setOnClickListener(this);
        tvNext.setOnClickListener(this);

    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_ill, null);
        progressBar = view.findViewById(R.id.pb_answer_ill);
        listView = view.findViewById(R.id.lv_answer_ill_investigate);
        tvUp = view.findViewById(R.id.tv_answer_ill_back);
        tvNext = view.findViewById(R.id.tv_answer_ill_next);
        containerView().addView(view);

    }

    private void initValues() {
        if (position == 0) {
            progressBar.setProgress(9);
            progressBar.setMax(12);
        } else if (position == 1) {
            progressBar.setProgress(7);
            progressBar.setMax(10);
        } else if (position == 5) {
            progressBar.setProgress(5);
            progressBar.setMax(8);
        } else {
            progressBar.setProgress(6);
            progressBar.setMax(9);
        }
        Log.i("yys", " progressBar.setProgress==" + progressBar.getProgress());
        Log.i("yys", " progressBar.setMax==" + progressBar.getMax());
        list.add(new EducationQuestionInvestigateModel("冠心病", "1", false));
        list.add(new EducationQuestionInvestigateModel("高血压", "2", false));
        list.add(new EducationQuestionInvestigateModel("合并神经病变", "3", false));
        list.add(new EducationQuestionInvestigateModel("合并视网膜病变", "4", false));
        list.add(new EducationQuestionInvestigateModel("合并肾病", "5", false));
        list.add(new EducationQuestionInvestigateModel("都没有", "6", false));

        adapter = new EducationQuestionInvestigateAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).getText().equals("都没有")) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getText().equals("都没有")) {
                        list.get(i).setCheck(false);
                    }
                }
                list.get(position).setCheck(!list.get(position).isCheck());
            }
            adapter.notifyDataSetChanged();
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_ill_back:
                finish();
                break;
            //下一题
            case R.id.tv_answer_ill_next:
                StringBuilder builder = new StringBuilder();
//                for (EducationQuestionInvestigateModel model : list) {
//                    //                    builder.append("{");
//                    builder.append(model.getId());
//                    //                    builder.append("}");
//                    builder.append(",");
//                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()){
                        builder.append(list.get(i).getId());
                        builder.append(",");
                    }
                }
                builder.deleteCharAt(builder.length() - 1);
                if (builder.length() == 0) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                    return;
                }
                Intent intent = new Intent(getPageContext(), AnswerMotionConditionsActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("height", getIntent().getStringExtra("height"));
                intent.putExtra("weight", getIntent().getStringExtra("weight"));
                intent.putExtra("strength", getIntent().getStringExtra("strength"));
                intent.putExtra("chronicDisease", getIntent().getStringExtra("chronicDisease"));
                intent.putExtra("illType", builder.toString());
                startActivity(intent);

                break;
            default:
                break;
        }
    }
}
