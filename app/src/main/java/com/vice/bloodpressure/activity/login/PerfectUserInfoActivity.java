package com.vice.bloodpressure.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 类名：
 * 传参：
 * 描述:
 * 完善信息
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
    /**
     * 糖尿病
     */
    private TextView tangTextView;
    /**
     * 高血压
     */
    private TextView gaoTextView;
    /**
     * 冠心病
     */
    private CheckBox guanCheckBox;
    /**
     * 脑卒中
     */
    private CheckBox naoCheckBox;
    private CheckBox feiCheckBox;
    private CheckBox qianCheckBox;
    private TextView sureTextView;

    private String born;
    private String tangType;
    private String gaoType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().backTextView().setVisibility(View.INVISIBLE);
        topViewManager().titleTextView().setText("完善个人信息");
        initView();
        initListener();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_perfect_born:
                PickerViewUtils.showTimeWindow(getPageContext(), new boolean[]{true, true, true, false, false, false}, DataFormatManager.TIME_FORMAT_Y_M_D, new CallBack() {
                    @Override
                    public void callBack(Object object) {
                        born = object.toString();
                        bornTextView.setText(object.toString());
                    }
                });
                break;
            case R.id.tv_perfect_tang:
                List<String> tangIllNessTypeList = new ArrayList<>();
                tangIllNessTypeList.add("无");
                tangIllNessTypeList.add("1型糖尿病");
                tangIllNessTypeList.add("2型糖尿病");
                tangIllNessTypeList.add("妊娠糖尿病");
                tangIllNessTypeList.add("其他");
                tangIllNessTypeList.add("未知");

                PickerViewUtils.showChooseSinglePicker(getPageContext(), "糖尿病", tangIllNessTypeList, object -> {
                            tangTextView.setText(tangIllNessTypeList.get(Integer.parseInt(String.valueOf(object))));
                            tangType = Integer.parseInt(String.valueOf(object)) + "";
                        }
                );
                break;
            case R.id.tv_perfect_gao:
                List<String> gaoIllNessTypeList = new ArrayList<>();
                gaoIllNessTypeList.add("无");
                gaoIllNessTypeList.add("1级高血压");
                gaoIllNessTypeList.add("2级高血压");
                gaoIllNessTypeList.add("3级高血压");
                gaoIllNessTypeList.add("未知");
                PickerViewUtils.showChooseSinglePicker(getPageContext(), "高血压", gaoIllNessTypeList, object -> {
                            gaoTextView.setText(gaoIllNessTypeList.get(Integer.parseInt(String.valueOf(object))));
                            gaoType = Integer.parseInt(String.valueOf(object)) + "";
                        }
                );
                break;
            case R.id.tv_perfect_start:
                sureToPerfect();
                break;
            default:
                break;
        }
    }

    private void sureToPerfect() {
        String name = nameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(born)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择出生年月");
            return;
        }

        if (TextUtils.isEmpty(tangType)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择糖尿病类型");
            return;
        }
        if (TextUtils.isEmpty(gaoType)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请选择高血压类型");
            return;
        }


        String gender = maleCheckBox.isChecked() ? "1" : "2";
        //chd
        String guan = guanCheckBox.isChecked() ? "0" : "1";
        //cva
        String nao = naoCheckBox.isChecked() ? "0" : "1";
        //copd
        String fei = feiCheckBox.isChecked() ? "0" : "1";
        //igr
        String qian = qianCheckBox.isChecked() ? "0" : "1";

        Call<String> requestCall = LoginDataManager.userPerfect(name, idCardEditText.getText().toString().trim(), born, gender, tangType, gaoType, guan, fei, nao, qian, UserInfoUtils.getArchivesId(getPageContext()), UserInfoUtils.getUserID(getPageContext()),UserInfoUtils.getAcceToken(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                Intent intent = new Intent(getPageContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }

        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userPerfect", requestCall);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backTip();
    }

    /**
     * 退出提示
     */
    private void backTip() {
        DialogUtils.showTipDialog(getPageContext(), "信息完善成功才可进入", ((dialog, which) -> {
            dialog.dismiss();
        }));
    }

    private void initListener() {
        bornTextView.setOnClickListener(this);
        gaoTextView.setOnClickListener(this);
        tangTextView.setOnClickListener(this);
        sureTextView.setOnClickListener(this);
        //        idCardEditText.addTextChangedListener(new TextWatcher() {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before, int count) {
        //
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s) {
        //                //从身份证号码中提取出生日期
        //                String birthDay;
        //                //拿到身份证号码文本
        //                IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor(s.toString().trim());
        //                birthDay = idCardInfo.getYear() + "-" + idCardInfo.getMonth() + "-" + idCardInfo.getDay();
        //                bornTextView.setText(birthDay);
        //            }
        //        });
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


    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_perfect_user_info_text, null);
        nameEditText = view.findViewById(R.id.et_perfect_name);
        idCardEditText = view.findViewById(R.id.et_perfect_id);
        bornTextView = view.findViewById(R.id.tv_perfect_born);
        maleCheckBox = view.findViewById(R.id.cb_perfect_male);
        femaleCheckBox = view.findViewById(R.id.cb_perfect_female);
        tangTextView = view.findViewById(R.id.tv_perfect_tang);
        gaoTextView = view.findViewById(R.id.tv_perfect_gao);
        guanCheckBox = view.findViewById(R.id.cb_perfect_guan);
        naoCheckBox = view.findViewById(R.id.cb_perfect_nao);
        feiCheckBox = view.findViewById(R.id.cb_perfect_fei);
        qianCheckBox = view.findViewById(R.id.cb_perfect_qian);
        sureTextView = view.findViewById(R.id.tv_perfect_start);
        containerView().addView(view);
    }
}
