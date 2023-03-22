package com.vice.bloodpressure.activity.aout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseLoadActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:医院详情
 */
public class OutHospitalInfoActivity extends UIBaseLoadActivity {
    private TextView backTexView;
    private TextView nameTexView;
    private TextView levelTexView;
    private TextView locationTexView;
    private TextView introduceTexView;
    private LinearLayout phoneLinearLayout;
    private String phoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("医院详情");
        initView();
        initListener();
    }

    private void initListener() {
        phoneLinearLayout.setOnClickListener(v -> {
            if (TextUtils.isEmpty(phoneNumber)) {
                phoneNumber = "15295201816";
            }
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));//跳转到拨号界面，同时传递电话号码
            startActivity(dialIntent);
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_out_hospital_info, null);
        backTexView = view.findViewById(R.id.tv_out_hos_info_bg);
        nameTexView = view.findViewById(R.id.tv_out_hos_info_name);
        levelTexView = view.findViewById(R.id.tv_out_hos_info_level);
        locationTexView = view.findViewById(R.id.tv_out_hos_info_location);
        phoneLinearLayout = view.findViewById(R.id.ll_out_hos_info_phone);
        introduceTexView = view.findViewById(R.id.tv_out_hos_info_introduce);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
