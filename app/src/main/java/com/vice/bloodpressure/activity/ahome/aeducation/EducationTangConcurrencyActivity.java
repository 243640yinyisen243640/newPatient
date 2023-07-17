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
 * 描述: 糖尿病并发症
 * 作者: beauty
 * 创建日期: 2023/7/13 16:35
 */
public class EducationTangConcurrencyActivity extends UIBaseActivity implements View.OnClickListener {
    private ListView listView;
    private TextView tvTitle;
    private TextView tvMore;
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;
    private ProgressBar progressBar;

    private List<Class> classList;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        intView();
        initValues();
        initListener();
    }

    private void initListener() {
        tvUp.setOnClickListener(this);
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
        //判断是下一题还是完成
        classList = (List<Class>) getIntent().getSerializableExtra("classList");
        index = getIntent().getIntExtra("index", 0);
        if (classList.size() == index + 1) {
            //最后一题  修改下一题为完成

        }
        //        progressBar.setProgress(6);
        //        progressBar.setMax(9);
        tvMore.setVisibility(View.VISIBLE);

        list.add(new BaseLocalDataInfo("无", "1"));
        list.add(new BaseLocalDataInfo("糖尿病足", "2"));
        list.add(new BaseLocalDataInfo("糖尿病肾病", "3"));
        list.add(new BaseLocalDataInfo("糖尿病视网膜病变", "4"));
        list.add(new BaseLocalDataInfo("糖尿病神经病变", "5"));
        list.add(new BaseLocalDataInfo("糖尿病下肢血管病变", "6"));

        adapter = new EducationQuestionInvestigateRealAdapter(list, getPageContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (list.get(position).getName().equals("无")) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals("无")) {
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
            case R.id.tv_answer_content_up:
                finish();
                break;
            //下一题
            case R.id.tv_answer_content_next:
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        builder.append(list.get(i).getId());
                        builder.append(",");
                    }
                }
                builder.deleteCharAt(builder.length() - 1);
                if (builder.length() == 0) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                    return;
                }


                //跳页面
                Intent intent = new Intent(getPageContext(),EducationTangKnowledgeActivity.class);
                intent.putExtra("index", index);
                intent.putExtra("classList", (Serializable) classList);
                startActivity(intent);


                break;
            default:
                break;
        }
    }
}
