package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.datamanager.UserDataManager;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:账户安全
 */
public class UserAccountSafeActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView editPwdTextView;
    private LinearLayout offLinearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("账户与安全");
        initView();
        initListener();
    }


    private void initListener() {
        editPwdTextView.setOnClickListener(this);
        offLinearLayout.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_account_safe, null);
        editPwdTextView = view.findViewById(R.id.tv_user_account_edit_pwd);
        offLinearLayout = view.findViewById(R.id.ll_user_account_off);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_account_edit_pwd:
                startActivity(new Intent(getPageContext(), UserEditPwdActivity.class));
                break;
            case R.id.ll_user_account_off:
                DialogUtils.showOperDialog(getPageContext(), "", "确定要注销吗？", "取消", "确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (XySoftDialogActionEnum.POSITIVE == which) {
                        sureToOffAccount();
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 注销
     */
    private void sureToOffAccount() {
        Call<String> requestCall = UserDataManager.offAccount((call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                UserInfoUtils.outlog(getPageContext(), null, null);
                Intent mainIntent = new Intent(getPageContext(), LoginActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("offAccount", requestCall);
    }
}
