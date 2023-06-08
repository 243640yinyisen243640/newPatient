package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:修改登录密码
 */
public class UserEditPwdActivity extends UIBaseActivity implements View.OnClickListener {
    /**
     * 原有的密码
     */
    private EditText beforeEditText;

    /**
     * 密码是否可见
     */
    private CheckBox closeCheckBox;

    private EditText passwordEditText;

    /**
     * 密码是否可见
     */
    private CheckBox closeAgainCheckBox;

    private EditText passwordAgainEditText;
    private TextView sureTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("修改登录密码");
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
        String beforePwd = beforeEditText.getText().toString().trim();
        if (TextUtils.isEmpty(beforePwd)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入原有密码");
            return;
        }
        String pwd = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请输入新密码");
            return;
        }
        String pwdAgain = passwordAgainEditText.getText().toString().trim();
        if (TextUtils.isEmpty(pwdAgain)) {
            ToastUtils.getInstance().showToast(getPageContext(), "请再次输入新密码");
            return;
        }

        if (!pwd.equals(pwdAgain)) {
            ToastUtils.getInstance().showToast(getPageContext(), "两次输入的密码不一致");
            return;
        }

        Call<String> requestCall = UserDataManager.editLoginPwd(UserInfoUtils.getUserID(getPageContext()), beforePwd, pwd, (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                Intent mainIntent = new Intent(getPageContext(), LoginActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editLoginPwd", requestCall);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_edit_sure:
                sureToEdit();
                break;
            default:
                break;
        }
    }


    private View initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_edit_pwd, null);
        beforeEditText = view.findViewById(R.id.et_user_edit_pwd_before);
        passwordEditText = view.findViewById(R.id.et_edit_pwd_new);
        closeCheckBox = view.findViewById(R.id.cb_user_edit_close);
        passwordAgainEditText = view.findViewById(R.id.et_edit_pwd_new_again);
        closeAgainCheckBox = view.findViewById(R.id.cb_user_edit_close_again);
        sureTextView = view.findViewById(R.id.tv_user_edit_sure);
        return view;
    }

    private void initListener() {
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
