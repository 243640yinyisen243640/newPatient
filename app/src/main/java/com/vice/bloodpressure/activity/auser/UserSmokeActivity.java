package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.vice.bloodpressure.R;
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
            if (yesCb.isChecked()) {
                if (TextUtils.isEmpty(smokeNumEt.getText().toString().trim())) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请输入数值");
                    return;
                }
            }
            editInfo();
        });

    }

    private void editInfo() {
        Call<String> requestCall = UserDataManager.editUserFilesInfoForSmoke(UserInfoUtils.getArchivesId(getPageContext()), yesCb.isChecked() ? "Y" : "N", TextUtils.isEmpty(smokeNumEt.getText().toString().trim()) ? "" : smokeNumEt.getText().toString().trim(), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                Intent intent = new Intent();
                intent.putExtra("isCheck", yesCb.isChecked() ? "Y" : "N");
                intent.putExtra("smokeNum", TextUtils.isEmpty(smokeNumEt.getText().toString().trim()) ? "" : smokeNumEt.getText().toString().trim());
                setResult(RESULT_OK, intent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editUserFilesInfoForSmoke", requestCall);
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
