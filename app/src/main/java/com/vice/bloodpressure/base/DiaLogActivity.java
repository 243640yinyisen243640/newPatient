package com.vice.bloodpressure.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.activity.login.LoginActivity;
import com.vice.bloodpressure.utils.UserInfoUtils;


/**
 * 类名：
 * 传参：
 * 描述:
 * 作者: beauty
 * 创建日期: 2024/3/6 10:27
 */
public class DiaLogActivity extends Activity implements View.OnClickListener {
    private static final int EXIT_LOGIN = 10086; // 退出登录

    private TextView contentTextView;//
    private TextView sureTextView; // 确定


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initView());
        initValues();
        initListener();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        initValues();
        initListener();
    }

    private View initView() {
        View view = View.inflate(this, R.layout.layout_package_no_same, null);
        contentTextView = view.findViewById(R.id.tv_dialog_no_same_car_content);
        sureTextView = view.findViewById(R.id.tv_dialog_no_same_car_right);
        return view;
    }

    private void initValues() {
        String content = getIntent().getStringExtra("msg");
        if (TextUtils.isEmpty(content)) {
            contentTextView.setText("您的账号已在另一台设备登录");
        } else {
            contentTextView.setText(content);
        }
        UserInfoUtils.outlog(getApplicationContext(), null, null);
    }

    private void initListener() {
        sureTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dialog_no_same_car_right:
                // 确定
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //        Intent intent = new Intent(this, LoginActivity.class);
        //        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //        startActivity(intent);
        //        finish();
    }
}
