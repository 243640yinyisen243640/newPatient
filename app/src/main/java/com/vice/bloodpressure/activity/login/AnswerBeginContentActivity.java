package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.home.EducationQuestionInvestigateAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.EducationQuestionInvestigateModel;
import com.vice.bloodpressure.utils.DensityUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class AnswerBeginContentActivity extends UIBaseActivity implements View.OnClickListener {
    //第几题
    private int questionNowNum;
    //一共几题 1是都没有 有五题的是糖尿病  21是冠心病  22慢性肺租塞 23脑卒中  3高血压
    private int questionAllNum = 5;
    //标题
    private String title;
    //是否多选
    private boolean isChooseMore;
    //是否有提示
    private boolean isQuestionTip;
    //是不是第一题
    private boolean isFirstQuestion;
    //是不是最后一题
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
        View view = View.inflate(getPageContext(), R.layout.activity_answer_content, null);
        containerView().addView(view);
        topViewManager().titleTextView().setText("问卷调查");

        progressBar = findViewById(R.id.pb_answer_content);
        tvTitle = findViewById(R.id.tv_answer_content_title);
        tvMoro = findViewById(R.id.tv_answer_content_more);
        tvTip = findViewById(R.id.tv_answer_content_tip);
        listView = findViewById(R.id.lv_answer_content_investigate);
        tvUp = findViewById(R.id.tv_answer_content_up);
        tvNext = findViewById(R.id.tv_answer_content_next);

        init();
        tvUp.setOnClickListener(this);
        tvNext.setOnClickListener(this);

    }

    private void init() {
        //进度
        questionNowNum = getIntent().getIntExtra("questionNowNum", 1);
        questionAllNum = getIntent().getIntExtra("questionAllNum", 5);
        progressBar.setMax(questionAllNum + 7);
        progressBar.setProgress(questionNowNum);

        initData();

        LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) tvNext.getLayoutParams();
        if (isFirstQuestion /*| isLastQuestion*/) {
            tvUp.setVisibility(View.GONE);
            lps.setMargins(DensityUtils.dip2px(getPageContext(), 27), 0, DensityUtils.dip2px(getPageContext(), 27), 0);
            if (isLastQuestion) {
                tvNext.setText("下一题");
            }
        } else {
            lps.setMargins(0, 0, 0, 0);
            tvUp.setVisibility(View.VISIBLE);
        }

        //标题
        tvTitle.setText(title);
        //是否多选
        tvMoro.setVisibility(isChooseMore ? View.VISIBLE : View.GONE);
        tvTip.setVisibility(isQuestionTip ? View.VISIBLE : View.GONE);

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
            case 1:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    default:
                        break;
                }
                break;
            case 21:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    case 2:
                        title = "您患病有多少时间了？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1~5年", false));
                        list.add(new EducationQuestionInvestigateModel("大于5年", false));
                        break;
                    default:
                        break;
                }
                break;
            case 22:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    case 2:
                        title = "您患病有多少时间了？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1~5年", false));
                        list.add(new EducationQuestionInvestigateModel("大于5年", false));
                        break;
                    default:
                        break;
                }
                break;
            case 23:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    case 2:
                        title = "您患病有多少时间了？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1~5年", false));
                        list.add(new EducationQuestionInvestigateModel("大于5年", false));
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    case 2:
                        title = "您患病有多少时间了？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1~5年", false));
                        list.add(new EducationQuestionInvestigateModel("大于5年", false));
                        break;
                    case 3:
                        title = "是否了解糖尿病的基础知识？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("是", false));
                        list.add(new EducationQuestionInvestigateModel("否", false));
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch (questionNowNum) {
                    case 1:
                        title = "您是否患有下列疾病？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = true;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病", false));
                        list.add(new EducationQuestionInvestigateModel("高血压", false));
                        list.add(new EducationQuestionInvestigateModel("冠心病", false));
                        list.add(new EducationQuestionInvestigateModel("慢性阻塞性肺疾病", false));
                        list.add(new EducationQuestionInvestigateModel("脑卒中", false));
                        list.add(new EducationQuestionInvestigateModel("都没有", false));
                        break;
                    case 2:
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
                    case 3:
                        title = "是否患有糖尿病并发症？";
                        isChooseMore = true;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("糖尿病足", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病肾病", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病眼病", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病神经病变", false));
                        list.add(new EducationQuestionInvestigateModel("糖尿病下肢血管病变", false));
                        list.add(new EducationQuestionInvestigateModel("无", false));
                        break;
                    case 4:
                        title = "是否了解糖尿病基础知识？";
                        isChooseMore = false;
                        isQuestionTip = true;
                        isFirstQuestion = false;
                        isLastQuestion = false;
                        list.add(new EducationQuestionInvestigateModel("是", false));
                        list.add(new EducationQuestionInvestigateModel("否", false));
                        break;
                    case 5:
                        title = "您患病有多少时间了？";
                        isChooseMore = false;
                        isQuestionTip = false;
                        isFirstQuestion = false;
                        isLastQuestion = true;
                        list.add(new EducationQuestionInvestigateModel("小于1年", false));
                        list.add(new EducationQuestionInvestigateModel("1~5年", false));
                        list.add(new EducationQuestionInvestigateModel("大于5年", false));
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
            case R.id.tv_answer_content_up:
                finish();
                break;
            //下一题
            case R.id.tv_answer_content_next:
                String answerMew = "";
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isCheck()) {
                        answerMew = answerMew + i + "_";
                    }
                }
                if (TextUtils.isEmpty(answerMew)) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请选择答案");
                    return;
                }
                answerMew = answerMew.substring(0, answerMew.length() - 1);
                if (isFirstQuestion) {
                    for (int i = 0; i < 5; i++) {
                        answer[i] = "";
                    }
                }
                answer[questionNowNum - 1] = answerMew;

                Intent intent = new Intent(getPageContext(), AnswerBeginContentActivity.class);
                if (isFirstQuestion && !isLastQuestion) {
                    if (list.get(0).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 5);
                        startActivity(intent);
                    } else if (list.get(1).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 3);
                        startActivity(intent);
                    } else if (list.get(2).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 21);
                        startActivity(intent);
                    } else if (list.get(3).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 22);
                        startActivity(intent);
                    } else if (list.get(4).isCheck()) {
                        intent.putExtra("questionNowNum", questionNowNum + 1);
                        intent.putExtra("questionAllNum", 23);
                        startActivity(intent);
                    } else {
                        Intent intent1 = new Intent(getPageContext(), AnswerHeightWeightActivity.class);
                        intent.putExtra("answer", answer);
                        startActivity(intent1);
                        Log.i("yys", "答案==" + "\n" + answer[0] + "\n" + answer[1] + "\n" + answer[2] + "\n" + answer[3] + "\n" + answer[4]);
                    }

                } else if (!isFirstQuestion && isLastQuestion) {
                    //是最后一题
                    Intent intent1 = new Intent(getPageContext(), AnswerHeightWeightActivity.class);
                    intent.putExtra("answer", answer);
                    startActivity(intent1);
                    Log.i("yys", "答案===" + "\n" + answer[0] + "\n" + answer[1] + "\n" + answer[2] + "\n" + answer[3] + "\n" + answer[4]);
                } else {
                    intent.putExtra("questionNowNum", questionNowNum + 1);
                    intent.putExtra("questionAllNum", getIntent().getIntExtra("questionAllNum", 5));
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
