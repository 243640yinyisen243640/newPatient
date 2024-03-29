package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.baseui.UIBaseActivity;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;
import com.vice.bloodpressure.utils.DialogUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:设置
 */
public class UserSetActivity extends UIBaseActivity implements View.OnClickListener {
    private TextView aboutAsTextView;
    private TextView accountTextView;
    private TextView outTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("设置");
        initView();
        initListener();
    }

    private void initListener() {
        aboutAsTextView.setOnClickListener(this);
        accountTextView.setOnClickListener(this);
        outTextView.setOnClickListener(this);
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_set, null);
        aboutAsTextView = view.findViewById(R.id.tv_user_set_about_us);
        accountTextView = view.findViewById(R.id.tv_user_set_account);
        outTextView = view.findViewById(R.id.tv_user_set_out_login);
        containerView().addView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user_set_about_us:
                startActivity(new Intent(getPageContext(), UserAboutAsActivity.class));
                break;
            case R.id.tv_user_set_account:
                startActivity(new Intent(getPageContext(), UserAccountSafeActivity.class));
                break;
            case R.id.tv_user_set_out_login:
                DialogUtils.showOperDialog(getPageContext(), "", "确定要退出登录吗？", "取消", "确定", (dialog, which) -> {
                    dialog.dismiss();
                    if (XySoftDialogActionEnum.POSITIVE == which) {
                        UserInfoUtils.outlog(getPageContext(), null, null);
                        Intent mainIntent = new Intent(getPageContext(), LoginActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                    }
                });
                break;
            default:
                break;
        }
    }
}
