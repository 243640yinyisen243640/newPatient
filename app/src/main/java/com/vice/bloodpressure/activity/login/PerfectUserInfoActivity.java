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
import com.vice.bloodpressure.adapter.login.PerfectDiseasePlusAdapter;
import com.vice.bloodpressure.baseimp.CallBack;
import com.vice.bloodpressure.baseimp.IAdapterViewClickListener;
import com.vice.bloodpressure.basemanager.DataFormatManager;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;
import com.vice.bloodpressure.view.HHAtMostGridView;

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
    private HHAtMostGridView otherHHAtMostGridView;
    private TextView sureTextView;

    private String born;
    private String tangType;
    private String gaoType;

    private PerfectDiseasePlusAdapter adapter;

    private List<BaseLocalDataInfo> diseaseList;

    private String diseases = "[no]";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().backTextView().setVisibility(View.INVISIBLE);
        topViewManager().titleTextView().setText("完善个人信息");
        initView();
        initValue();
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
        StringBuilder builder = new StringBuilder();
        if (diseaseList != null && diseaseList.size() > 0) {
            StringBuilder paramStringBuilder = new StringBuilder();
            paramStringBuilder.append("{");
            for (int i = 0; i < diseaseList.size(); i++) {
                if (diseaseList.get(i).getIsCheck()) {
                    builder.append(diseaseList.get(i).getId());
                    builder.append(",");
                }
            }

            if (!tangType.equals("0")) {
                builder.append("dm");
            }
            if (!gaoType.equals("0")) {
                builder.append("htn");
            }
            paramStringBuilder.deleteCharAt(paramStringBuilder.length() - 1);
            paramStringBuilder.append("}");
            diseases = paramStringBuilder.toString();
        }

        Call<String> requestCall = LoginDataManager.userPerfect(name, idCardEditText.getText().toString().trim(), born, gender, diseases, tangType, gaoType, UserInfoUtils.getArchivesId(getPageContext()), (call, response) -> {
            if ("0000".equals(response.code)) {
                UserInfo userInfo = (UserInfo) response.object;
                //                UserInfoUtils.saveLoginInfo(getPageContext(), userInfo);
                Intent intent = new Intent(getPageContext(), PerfectUserInfoActivity.class);
                //                intent.putExtra("userInfo", userInfo);
                startActivity(intent);
                finish();
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            //            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("userRegister", requestCall);
    }

    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
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

    private void initValue() {
        diseaseList = new ArrayList<>();
        BaseLocalDataInfo typeInfo11 = new BaseLocalDataInfo("冠心病", "chd");
        diseaseList.add(typeInfo11);
        BaseLocalDataInfo typeInfo12 = new BaseLocalDataInfo("脑卒中", "cva");
        diseaseList.add(typeInfo12);
        BaseLocalDataInfo typeInfo13 = new BaseLocalDataInfo("慢性阻塞性肺疾病", "copd");
        diseaseList.add(typeInfo13);
        BaseLocalDataInfo typeInfo14 = new BaseLocalDataInfo("糖尿病前期", "igr");
        diseaseList.add(typeInfo14);

        adapter = new PerfectDiseasePlusAdapter(getPageContext(), diseaseList, new IAdapterViewClickListener() {
            @Override
            public void adapterClickListener(int position, View view) {
                switch (view.getId()) {
                    case R.id.tv_perfect:
                        diseaseList.get(position).setIsCheck(!diseaseList.get(position).getIsCheck());
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void adapterClickListener(int position, int index, View view) {

            }
        });
        otherHHAtMostGridView.setAdapter(adapter);
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
        otherHHAtMostGridView = view.findViewById(R.id.gv_perfect_other);
        sureTextView = view.findViewById(R.id.tv_perfect_start);
        containerView().addView(view);
    }
}
