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
 * 描述:我的医生
 */
public class UserDoctorActivity extends UIBaseLoadActivity {
    private ImageView headImageView;
    private TextView nameTextView;
    private TextView postTextView;
    private TextView hospitalTextView;
    private TextView introduceTextView;
    private TextView breakTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("我的医生");
        initView();
        initlistener();
    }

    private void initlistener() {
        breakTextView.setOnClickListener(v -> {
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_my_doctor, null);
        headImageView = view.findViewById(R.id.iv_user_my_doctor_head);
        nameTextView = view.findViewById(R.id.tv_user_my_doctor_name);
        postTextView = view.findViewById(R.id.tv_user_my_doctor_post);
        hospitalTextView = view.findViewById(R.id.tv_user_my_doctor_hos);
        introduceTextView = view.findViewById(R.id.tv_user_my_doctor_introduce);
        breakTextView = view.findViewById(R.id.tv_user_my_doctor_break);
        containerView().addView(view);
    }

    @Override
    protected void onPageLoad() {

    }
}
