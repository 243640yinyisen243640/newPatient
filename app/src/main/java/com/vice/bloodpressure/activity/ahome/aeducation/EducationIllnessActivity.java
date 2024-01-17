package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.MainActivity;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateRealAdapter;
import com.vice.bloodpressure.baseui.SharedPreferencesConstant;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.HomeDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.EducationAnswerInfo;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.SharedPreferencesUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

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
    private TextView tvLaterMore;
    private EducationQuestionInvestigateRealAdapter adapter;
    private List<BaseLocalDataInfo> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;
    private ProgressBar progressBar;
    //身高
    private String height;
    //体重
    private String weight;
    private EducationAnswerInfo answerInfo;
    private List<Class> classList = new ArrayList<>();
    private int allPage;
    private int page;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("制定教育方案");
        height = getIntent().getStringExtra("height");
        weight = getIntent().getStringExtra("weight");
        intView();
        initValues();
        initListener();
    }

    private void initListener() {
        tvNext.setOnClickListener(this);
        tvUp.setOnClickListener(this);
    }

    private void intView() {
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        progressBar = view.findViewById(R.id.pb_answer_content);
        tvTitle = view.findViewById(R.id.tv_answer_content_title);
        tvMore = view.findViewById(R.id.tv_answer_content_more);
        tvLaterMore = view.findViewById(R.id.tv_answer_content_more_later);
        listView = view.findViewById(R.id.lv_answer_content_investigate);
        tvUp = view.findViewById(R.id.tv_answer_content_up);
        tvNext = view.findViewById(R.id.tv_answer_content_next);
        containerView().addView(view);

    }

    private void initValues() {
        progressBar.setProgress(2);
        progressBar.setMax(11);
        tvMore.setVisibility(View.VISIBLE);
        tvLaterMore.setVisibility(View.VISIBLE);
        tvTitle.setText("您患有以下哪个主要疾病？");

        list.add(new BaseLocalDataInfo("糖尿病", "1", EducationAnswerTangActivity.class, 4));
        list.add(new BaseLocalDataInfo("高血压", "2", EducationGaoTimeActivity.class, 2));
        list.add(new BaseLocalDataInfo("冠心病", "3", EducationGuanTimeActivity.class, 1));
        list.add(new BaseLocalDataInfo("慢性阻塞性肺疾病", "4", EducationFeiTimeActivity.class, 1));
        list.add(new BaseLocalDataInfo("糖尿病前期", "5", EducationTangQianKnowledgeActivity.class, 1));
        list.add(new BaseLocalDataInfo("脑卒中", "6", EducationNaoTimeActivity.class, 1));
        list.add(new BaseLocalDataInfo("都没有", "7"));
        list.get(6).setCheck(true);
        tvNext.setText("完成");
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_answer_content_up:
                finish();
                break;
            //下一题
            case R.id.tv_answer_content_next:
                classList = new ArrayList<>();
                allPage = 11;
                page = 2;

                //                if (classList.size() == 0) {
                //                    ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                //                    return;
                //                }

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        builder.append(list.get(i).getId());
                        builder.append(",");
                        classList.add(list.get(i).getClassName());
                        allPage = allPage + list.get(i).getPage();
                    }
                }
                builder.deleteCharAt(builder.length() - 1);


                answerInfo = new EducationAnswerInfo();

                answerInfo.setHeight(height);
                answerInfo.setWeight(weight);
                answerInfo.setMainDisease(builder.toString());

                if (classList.size() == 1 && classList.get(0) == null) {
                    //都没有
                    sendAnswer();

                } else {
                    //跳转页面
                    int index = 0;
                    Intent intent = new Intent(this, classList.get(index));
                    intent.putExtra("index", index);
                    intent.putExtra("answerInfo", answerInfo);
                    intent.putExtra("page", page + 1);
                    intent.putExtra("allPage", allPage);
                    intent.putExtra("classList", (Serializable) classList);
                    //其他的你自己传
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 提交答案
     */
    private void sendAnswer() {
        Call<String> requestCall = HomeDataManager.educationAddAnswer(SharedPreferencesUtils.getInfo(getPageContext(), SharedPreferencesConstant.ARCHIVES_ID),answerInfo.getHeight(), answerInfo.getWeight(), "7", "", "", "", "", "", "", "", "", "", (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                Intent intent = new Intent(getPageContext(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("educationAddAnswer", requestCall);
    }
}
