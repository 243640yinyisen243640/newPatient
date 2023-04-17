package com.vice.bloodpressure.activity.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.LoginDataManager;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ForgetPwdActivity extends UIBaseActivity implements View.OnClickListener {
    /**
     * 手机号
     */
    private EditText phoneEditText;
    /**
     * 验证码
     */
    private EditText verificationEditText;
    /**
     * 获取验证码
     */
    private TextView getVerTextView;

    /**
     * 密码是否可见
     */
    private CheckBox closeCheckBox;
    /**
     * 密码
     */
    private EditText passwordEditText;

    /**
     * 密码是否可见
     */
    private CheckBox closeAgainCheckBox;
    /**
     * 再次输入密码
     */
    private EditText passwordAgainEditText;
    private TextView sureTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("忘记密码");
        containerView().addView(initView());
        initValue();
        initListener();
    }

    private void initValue() {
        //显示密码不可见
        passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
        passwordAgainEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        passwordAgainEditText.setSelection(passwordAgainEditText.getText().toString().trim().length());
    }

    private void sureToEdit() {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号码");
            return;
        }


        //        String verification = verificationEditText.getText().toString().trim();
        String verification = "0000";
        if (TextUtils.isEmpty(verification)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入验证码");
            return;
        }

        String pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入密码");
            return;
        }
        String pwdAgain = passwordAgainEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwdAgain)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入密码");
            return;
        }

        if (!pwd.equals(pwdAgain)) {
            ToastUtils.getInstance().showToast(getPageContext(), "两次密码输入的不一致");
            return;
        }

        Call<String> requestCall = LoginDataManager.forgetPwd(phone, pwd, verification, (call, response) -> {
            if ("0000".equals(response.code)) {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            } else {
                ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("forgetPwd", requestCall);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_pwd_sure:
                sureToEdit();
                break;
            case R.id.tv_forget_pwd_get:
                getVerifiCode();
                break;

            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifiCode() {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入手机号码");
            return;
        }

        //        ToastUtils.getInstance().showProgressDialog(getPageContext(), R.string.waiting, false);
        //        Call<String> requestCall = LoginDataManager.verifyCodeByTel(phone, "1", (call, response) -> {
        //            ToastUtils.getInstance().dismissProgressDialog();
        //            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
        //            if (100 == response.code) {
        //                CountDownTask.getInstence().showTimer(getVerTextView, 120, getPageContext());
        //            }
        //        }, (call, throwable) -> {
        //            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        //        });
        //        addRequestCallToMap("verifyCodeByTel", requestCall);
    }

    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_forget_pwd, null);
        phoneEditText = view.findViewById(R.id.et_forget_pwd_phone);
        verificationEditText = view.findViewById(R.id.et_forget_pwd_code);
        getVerTextView = view.findViewById(R.id.tv_forget_pwd_get);
        passwordEditText = view.findViewById(R.id.et_forget_pwd_pwd);
        closeCheckBox = view.findViewById(R.id.cb_forget_pwd_close);
        passwordAgainEditText = view.findViewById(R.id.et_forget_pwd_pwd_again);
        closeAgainCheckBox = view.findViewById(R.id.cb_forget_pwd_close_again);
        sureTextView = view.findViewById(R.id.tv_forget_pwd_sure);

        return view;
    }

    private void initListener() {
        getVerTextView.setOnClickListener(this);
        sureTextView.setOnClickListener(this);
        closeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //显示密码不可见
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
            } else {
                //显示明文可见
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordEditText.setSelection(passwordEditText.getText().toString().trim().length());
            }
        });
        closeAgainCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                //显示密码不可见
                passwordAgainEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                passwordAgainEditText.setSelection(passwordAgainEditText.getText().toString().trim().length());
            } else {
                //显示明文可见
                passwordAgainEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordAgainEditText.setSelection(passwordAgainEditText.getText().toString().trim().length());
            }
        });
    }
}
