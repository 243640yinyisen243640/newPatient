package com.vice.bloodpressure.activity.auser;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserQRCodeActivity extends UIBaseLoadActivity {
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView phoneTextView;
    private ImageView qrImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的二维码");
        initView();
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_center_qr_code, null);
        containerView().addView(view);
        headImageView = view.findViewById(R.id.iv_user_qr_avatar);
        nameTextView = view.findViewById(R.id.tv_user_qr_nickname);
        phoneTextView = view.findViewById(R.id.tv_user_qr_phone);
        qrImageView = view.findViewById(R.id.iv_user_qr_code);

    }

    @Override
    protected void onPageLoad() {


    }
}
