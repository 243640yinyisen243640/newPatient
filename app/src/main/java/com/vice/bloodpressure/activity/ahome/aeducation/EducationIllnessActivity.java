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
import com.vice.bloodpressure.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述: 您患有以下哪个主要疾病
 * 作者: beauty
 * 创建日期: 2023/7/13 16:33
 */
public class EducationIllnessActivity extends UIBaseActivity implements View.OnClickListener {
    private ListView listView;
    private TextView tvTitle;
    private TextView tvMore;
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        intView();
        initValues();
        initListener();

    }

    private void initListener() {
        tvNext.setOnClickListener(this);
    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMore = view.findViewById(R.id.tv_answer_content_more);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        tvUp = view.findViewById(R.id.tv_answer_content_up);
        tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

    }

    private void initValues() {
        //        progressBar.setProgress(6);
        //        progressBar.setMax(9);
        tvMore.setVisibility(View.VISIBLE);
        tvTitle.setText("您患有以下哪个主要疾病？");
        list.add(new BaseLocalDataInfo("糖尿病", "1", EducationAnswerTangActivity.class));
        list.add(new BaseLocalDataInfo("高血压", "2", EducationGaoTimeActivity.class));
        list.add(new BaseLocalDataInfo("冠心病", "3", EducationGuanTimeActivity.class));
        list.add(new BaseLocalDataInfo("慢性阻塞性肺疾病", "4", EducationFeiTimeActivity.class));
        list.add(new BaseLocalDataInfo("糖尿病前期", "5", EducationTangQianKnowledgeActivity.class));
        list.add(new BaseLocalDataInfo("脑卒中", "6", EducationNaoTimeActivity.class));
        list.add(new BaseLocalDataInfo("都没有", "7"));

        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).getName().equals("都没有")) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
                tvNext.setText("完成");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals("都没有")) {
                        list.get(i).setCheck(false);
                    }
                }
                list.get(position).setCheck(!list.get(position).isCheck());
                tvNext.setText("下一题");
            }
            adapter.notifyDataSetChanged();
        });
    }

    private List<Class> classList;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_content_up:
                finish();
                break;
            //下一题
            case R.id.tv_answer_content_next:
                if (list.size() == 0) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                    return;
                }
                classList = new ArrayList<>();
                if (list.size() == 1 && list.get(0).getId().equals(7)) {
                    //都没有

                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isCheck()){
                            classList.add(list.get(i).getClassName());
                        }
                    }
                    //跳转页面
                    int index = 0;
                    Intent intent = new Intent(this, classList.get(index));
                    intent.putExtra("index", index);
                    intent.putExtra("classList", (Serializable) classList);
                    //其他的你自己传
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
