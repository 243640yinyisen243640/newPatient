package com.vice.bloodpressure.activity.ahome.aeducation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

public class EducationQuestionInvestigateActivity extends UIBaseActivity implements View.OnClickListener {
    //第几题
    private int questionNowNum;
    //一共几题
    private int questionAllNum = 6;
    //标题
    private String title;
    //是否多选
    private boolean isChooseMore;
    //是否有提示
    private boolean isQuestionTip;

    private boolean isFirstQuestion;
    private boolean isLastQuestion;

    private static final String[] answer = {"", "", "", "", "", ""};


    private ProgressBar progressBar;
    private TextView tvTitle;
    private TextView tvMoro;
    private TextView tvTip;
    private ListView listView;
    private EducationQuestionInvestigateAdapter adapter;
    private List<EducationQuestionInvestigateModel> list = new ArrayList<>();

    private TextView tvUp;
    private TextView tvNext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getPageContext(), R.layout.education_question_investigate, null);
        topViewManager().titleTextView().setText("问卷调查");

        progressBar = view.findViewById(R.id.pb_education_question_investigate);
        tvTitle = view.findViewById(R.id.tv_education_question_investigate_title);
        tvMoro = view.findViewById(R.id.tv_education_question_investigate_more);
        tvTip = view.findViewById(R.id.tv_education_question_investigate_tip);
        listView = view.findViewById(R.id.lv_education_question_investigate);
        tvUp = view.findViewById(R.id.tv_education_question_investigate_up);
        tvNext = view.findViewById(R.id.tv_education_question_investigate_next);
        containerView().addView(view);

        init();
        tvUp.setOnClickListener(this);
        tvNext.setOnClickListener(this);

    }

    private void init() {
        //进度
        questionNowNum = getIntent().getIntExtra("questionNowNum", 1);
        questionAllNum = getIntent().getIntExtra("questionAllNum", 6);
        progressBar.setMax(questionAllNum);
        progressBar.setProgress(questionNowNum);

        initData();

        LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) tvNext.getLayoutParams();
        if (isFirstQuestion | isLastQuestion) {
            tvUp.setVisibility(View.GONE);
            lps.setMargins(DensityUtils.dip2px(getPageContext(), 27), 0, DensityUtils.dip2px(getPageContext(), 27), 0);
            if (isLastQuestion) {
                tvNext.setText("完成");
            }
        } else {
            lps.setMargins(0, 0, 0, 0);
            tvUp.setVisibility(View.VISIBLE);
        }

        //标题
        tvTitle.setText(title);
        //是否多选
        tvMoro.setVisibility(isChooseMore ? View.VISIBLE : View.INVISIBLE);
        tvTip.setVisibility(isQuestionTip ? View.VISIBLE : View.INVISIBLE);

        adapter = new EducationQuestionInvestigateAdapter(list, getPageContext());
        listView.setAdapter(adapter);
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
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setCheck(false);
                }
                list.get(position).setCheck(true);
            }
            adapter.notifyDataSetChanged();
        });
    }

    private void initData() {
        switch (questionAllNum) {
            case 3:
                switch (questionNowNum) {
                    case 1:
                        title = "是否患有糖尿病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("是", false));
                        list.add(new EducationQuestionInvestigateModel("否", false));
                        break;
                    case 2:
                        title = "请选择年龄范围";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("小于40岁", false));
                        list.add(new EducationQuestionInvestigateModel("40-65岁", false));
                        list.add(new EducationQuestionInvestigateModel("大于65岁", false));
                        break;
                    case 3:
                        title = "是否患有以下疾病？";
                        isChooseMore = true;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("无", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病前期", false));
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch (questionNowNum) {
                    case 1:
                        title = "是否患有糖尿病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("是", false));
                        list.add(new EducationQuestionInvestigateModel("否", false));
                        break;
                    case 2:
                        title = "是否了解糖尿病基础知识？";
                        isChooseMore = false;
                        isQuestionTip = true;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("是", false));
                        list.add(new EducationQuestionInvestigateModel("否", false));
                        break;
                    case 3:
                        title = "请选择糖尿病类型？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("1型糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("2型糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("妊娠糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("其他类型", false));
                        break;
                    case 4:
                        title = "请选择年龄范围";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("小于40岁", false));
                        list.add(new EducationQuestionInvestigateModel("40-65岁", false));
                        list.add(new EducationQuestionInvestigateModel("大于65岁", false));
                        break;
                    case 5:
                        title = "是否患有糖尿病并发症？";
                        isChooseMore = true;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("无", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病足", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病肾病", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病眼病", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病神经病变", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病下肢血管病变", false));
                        break;
                    case 6:
                        title = "请选择病程";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1-5年", false));
                        list.add(new EducationQuestionInvestigateModel("5年以上", false));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_education_question_investigate_up:
                finish();
                break;
            //下一题
            case R.id.tv_education_question_investigate_next:
                String answerMew = "";
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        answerMew = answerMew + (i+1) + ",";

                     }
                }
                if (TextUtils.isEmpty(answerMew)) {
                    Toast.makeText(getPageContext(), "请选择答案", Toast.LENGTH_SHORT).show();
                    return;
                }
                answerMew = answerMew.substring(0, answerMew.length() - 1);
                if (isFirstQuestion) {
                    for (int i = 0; i < 6; i++) {
                        answer[i] = "";
                    }
                }
                answer[questionNowNum - 1] = answerMew;

                Intent intent = new Intent(getPageContext(), EducationQuestionInvestigateActivity.class);
                if (isFirstQuestion) {
                    if (list.get(0).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 6);
                    } else {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 3);
                    }
                    startActivity(intent);
                } else if (isLastQuestion) {
                    //是最后一题
                    Log.i("yys", "答案=="
                            + "\n" + answer[0]
                            + "\n" + answer[1]
                            + "\n" + answer[2]
                            + "\n" + answer[3]

                            + "\n" + answer[5]);
                } else {
                    intent.putExtra("questionNowNum", questionNowNum + 1);
                    intent.putExtra("questionAllNum", getIntent().getIntExtra("questionAllNum", 6));
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
