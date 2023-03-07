package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.adapter.login.PerfectDiseaseAdapter;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.model.IDCardInfoExtractor;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.view.HHAtMostGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：
 * 传参：
 * 描述:完善信息
 * 作者: beauty
 * 创建日期: 2023/3/2 16:25
 */
public class PerfectUserInfoActivity extends UIBaseActivity implements View.OnClickListener {
    /**
     * 姓名
     */
    private EditText nameEditText;
    /**
     * 身份证号
     */
    private EditText idCardEditText;
    /**
     * 出生年月
     */
    private TextView bornTextView;
    /**
     * 男
     */
    private CheckBox maleCheckBox;
    /**
     * 女
     */
    private CheckBox femaleCheckBox;
    private HHAtMostGridView tangHHAtMostGridView;
    private HHAtMostGridView yaHHAtMostGridView;
    private TextView sureTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().backImageView().setVisibility(View.INVISIBLE);
        topViewManager().titleTextView().setText("完善个人信息");
        initView();
        initValue();
        initListener();
    }

    private void initListener() {
        bornTextView.setOnClickListener(this);
        sureTextView.setOnClickListener(this);
        idCardEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //从身份证号码中提取出生日期
                String birthDay;
                //拿到身份证号码文本
                IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor(s.toString().trim());
                birthDay = idCardInfo.getYear() + "-" + idCardInfo.getMonth() + "-" + idCardInfo.getDay();
                bornTextView.setText(birthDay);
            }
        });
        maleCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                femaleCheckBox.setChecked(false);
            } else {
                femaleCheckBox.setChecked(true);
            }
        });
        femaleCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                maleCheckBox.setChecked(false);
            } else {
                maleCheckBox.setChecked(true);
            }
        });
    }

    private void initValue() {
        List<UserInfo> diseaseList = new ArrayList<>();
        UserInfo typeInfo1 = new UserInfo("无", "1");
        diseaseList.add(typeInfo1);
        UserInfo typeInfo2 = new UserInfo("1型糖尿病", "2");
        diseaseList.add(typeInfo2);
        UserInfo typeInfo3 = new UserInfo("2型糖尿病", "3");
        diseaseList.add(typeInfo3);
        UserInfo typeInfo4 = new UserInfo("妊娠糖尿病", "4");
        diseaseList.add(typeInfo4);
        UserInfo typeInfo5 = new UserInfo("其他", "5");
        diseaseList.add(typeInfo5);
        UserInfo typeInfo6 = new UserInfo("未知", "6");
        diseaseList.add(typeInfo6);
        PerfectDiseaseAdapter adapter = new PerfectDiseaseAdapter(getPageContext(), diseaseList);
        tangHHAtMostGridView.setAdapter(adapter);


        List<UserInfo> diseaseList2 = new ArrayList<>();
        UserInfo typeInfo11 = new UserInfo("无", "1");
        diseaseList2.add(typeInfo11);
        UserInfo typeInfo12 = new UserInfo("1级高血压", "2");
        diseaseList2.add(typeInfo12);
        UserInfo typeInfo13 = new UserInfo("2级高血压", "3");
        diseaseList2.add(typeInfo13);
        UserInfo typeInfo14 = new UserInfo("3级高血压", "4");
        diseaseList2.add(typeInfo14);
        UserInfo typeInfo15 = new UserInfo("未知", "5");
        diseaseList2.add(typeInfo15);

        PerfectDiseaseAdapter adapter2 = new PerfectDiseaseAdapter(getPageContext(), diseaseList2);
        yaHHAtMostGridView.setAdapter(adapter2);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_perfect_user_info_text, null);
        nameEditText = view.findViewById(R.id.et_perfect_name);
        idCardEditText = view.findViewById(R.id.et_perfect_id);
        bornTextView = view.findViewById(R.id.tv_perfect_born);
        maleCheckBox = view.findViewById(R.id.cb_perfect_male);
        femaleCheckBox = view.findViewById(R.id.cb_perfect_female);
        tangHHAtMostGridView = view.findViewById(R.id.gv_perfect_tang);
        yaHHAtMostGridView = view.findViewById(R.id.gv_perfect_ya);
        sureTextView = view.findViewById(R.id.tv_perfect_start);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_perfect_born:
                //                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                //                    @Override
                //                    public void callBack(Object object) {
                //                        String born = object.toString();
                //                        bornTextView.setText(object.toString());
                //                    }
                //                });
                break;
            case R.id.tv_perfect_start:
                startActivity(new Intent(getPageContext(), AnswerBeginActivity.class));
                break;
            default:
                break;
        }
    }
}
