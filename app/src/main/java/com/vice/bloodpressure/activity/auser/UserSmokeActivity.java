package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseui.UIBaseActivity;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:抽烟的编辑数量
 */
public class UserSmokeActivity extends UIBaseActivity {

    private TextView titleTextView;
    private CheckBox yesCb;
    private CheckBox noCb;
    private LinearLayout smokeLinerLayout;
    private EditText smokeNumEt;
    private ImageView arrowImageView;
    private LinearLayout drinkLinerLayout;
    private TextView sureTv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("吸烟");
        initView();
        initListener();
    }

    private void initListener() {
        yesCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                noCb.setChecked(false);
                arrowImageView.setVisibility(View.VISIBLE);
                smokeLinerLayout.setVisibility(View.VISIBLE);
            } else {
                noCb.setChecked(true);
                arrowImageView.setVisibility(View.GONE);
                smokeLinerLayout.setVisibility(View.GONE);
            }
        });
        noCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                arrowImageView.setVisibility(View.GONE);
                smokeLinerLayout.setVisibility(View.GONE);
                yesCb.setChecked(false);
            } else {
                arrowImageView.setVisibility(View.VISIBLE);
                smokeLinerLayout.setVisibility(View.VISIBLE);
                yesCb.setChecked(true);
            }
        });


        sureTv.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("isCheck", yesCb.isChecked() ? "1" : "0");
            intent.putExtra("smokeNum", smokeNumEt.getText().toString().trim());
            setResult(RESULT_OK, intent);
            finish();
        });

    }



    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_smoke_drink, null);
        containerView().addView(view);
        titleTextView = view.findViewById(R.id.tv_files_smoke_drink_title);
        yesCb = view.findViewById(R.id.cb_files_smoke_drink_yes);
        noCb = view.findViewById(R.id.cb_files_smoke_drink_no);
        smokeNumEt = view.findViewById(R.id.et_files_smoke_drink_smoke_num);
        arrowImageView = view.findViewById(R.id.iv_files_smoke);
        smokeLinerLayout = view.findViewById(R.id.ll_files_smoke);
        drinkLinerLayout = view.findViewById(R.id.ll_files_drink);
        sureTv = view.findViewById(R.id.tv_files_smoke_drink_sure);
        titleTextView.setText("是否吸烟");
        smokeLinerLayout.setVisibility(View.VISIBLE);
        drinkLinerLayout.setVisibility(View.GONE);
    }

}
