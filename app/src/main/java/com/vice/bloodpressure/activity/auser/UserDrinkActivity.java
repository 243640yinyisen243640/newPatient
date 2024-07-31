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
import com.vice.bloodpressure.utils.PickerViewUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.UserInfoUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:喝酒的编辑数量
 */
public class UserDrinkActivity extends UIBaseActivity {

    private TextView titleTextView;
    private CheckBox yesCb;
    private CheckBox noCb;
    private LinearLayout smokeLinerLayout;
    private ImageView arrowImageView;
    private TextView typeTv;
    private EditText drinkNumEt;
    private LinearLayout drinkLinerLayout;
    private TextView sureTv;


    private String drinkType = "1";
    private String drinkName="红酒";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topViewManager().titleTextView().setText("饮酒");
        initView();
        initListener();
    }

    private void initListener() {
        yesCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                noCb.setChecked(false);
                arrowImageView.setVisibility(View.VISIBLE);
                drinkLinerLayout.setVisibility(View.VISIBLE);
            } else {
                noCb.setChecked(true);
                arrowImageView.setVisibility(View.GONE);
                drinkLinerLayout.setVisibility(View.GONE);
            }
        });
        noCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                arrowImageView.setVisibility(View.GONE);
                drinkLinerLayout.setVisibility(View.GONE);
                yesCb.setChecked(false);
            } else {
                arrowImageView.setVisibility(View.VISIBLE);
                drinkLinerLayout.setVisibility(View.VISIBLE);
                yesCb.setChecked(true);
            }
        });

        typeTv.setOnClickListener(v -> {
            chooseDrinkTypeWindow();
        });
        sureTv.setOnClickListener(v -> {
            if (yesCb.isChecked()) {
                if (TextUtils.isEmpty(drinkNumEt.getText().toString().trim())) {
                    ToastUtils.getInstance().showToast(getPageContext(), "请输入数值");
                    return;
                }
            }

            editInfo();
        });

    }

    private void editInfo() {
        Call<String> requestCall = UserDataManager.editUserFilesInfoForDrink(UserInfoUtils.getArchivesId(getPageContext()), yesCb.isChecked() ? "Y" : "N", drinkType, TextUtils.isEmpty(drinkNumEt.getText().toString().trim()) ? "" : drinkNumEt.getText().toString().trim(), (call, response) -> {
            ToastUtils.getInstance().showToast(getPageContext(), response.msg);
            if ("0000".equals(response.code)) {
                Intent intent = new Intent();
                intent.putExtra("isCheck", yesCb.isChecked() ? "Y" : "N");
                intent.putExtra("drinkNum", TextUtils.isEmpty(drinkNumEt.getText().toString().trim()) ? "" : drinkNumEt.getText().toString().trim());
                intent.putExtra("drinkType", drinkType);
                intent.putExtra("drinkName", drinkName);
                setResult(RESULT_OK, intent);
                finish();
            }
        }, (call, t) -> {
            ResponseUtils.defaultFailureCallBack(getPageContext(), call);
        });
        addRequestCallToMap("editUserFilesInfoForDrink", requestCall);
    }

    /**
     * 酒的种类
     * 1->红酒;2->啤酒;3->白酒; 4 黄酒
     */
    private void chooseDrinkTypeWindow() {
        List<String> typeList = new ArrayList<>();
        typeList.add("红酒");
        typeList.add("啤酒");
        typeList.add("白酒");
        typeList.add("黄酒");
        PickerViewUtils.showChooseSinglePicker(getPageContext(), "酒类", typeList, object -> {
            typeTv.setText(typeList.get(Integer.parseInt(String.valueOf(object))));
            drinkType = Integer.parseInt(String.valueOf(object)) + 1 + "";
            drinkName = typeList.get(Integer.parseInt(String.valueOf(object)));
        });
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_smoke_drink, null);
        containerView().addView(view);
        titleTextView = view.findViewById(R.id.tv_files_smoke_drink_title);
        yesCb = view.findViewById(R.id.cb_files_smoke_drink_yes);
        noCb = view.findViewById(R.id.cb_files_smoke_drink_no);
        arrowImageView = view.findViewById(R.id.iv_files_smoke);
        smokeLinerLayout = view.findViewById(R.id.ll_files_smoke);
        typeTv = view.findViewById(R.id.tv_files_smoke_drink_type);
        drinkNumEt = view.findViewById(R.id.et_files_smoke_drink_num);
        drinkLinerLayout = view.findViewById(R.id.ll_files_drink);
        sureTv = view.findViewById(R.id.tv_files_smoke_drink_sure);
        titleTextView.setText("是否饮酒");
        smokeLinerLayout.setVisibility(View.GONE);
        drinkLinerLayout.setVisibility(View.VISIBLE);
    }

}
