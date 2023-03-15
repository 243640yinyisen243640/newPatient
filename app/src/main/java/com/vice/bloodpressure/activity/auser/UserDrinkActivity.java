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
import com.vice.bloodpressure.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.List;

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


    private String drinkType = "0";
    private String drinkName;

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
            Intent intent = new Intent();
            intent.putExtra("isCheck", yesCb.isChecked() ? "1" : "0");
            intent.putExtra("drinkNum", TextUtils.isEmpty(drinkNumEt.getText().toString().trim()) ? "" : drinkNumEt.getText().toString().trim());
            intent.putExtra("drinkType", drinkType);
            intent.putExtra("drinkName", drinkName);
            setResult(RESULT_OK, intent);
            finish();
        });

    }


    /**
     * 酒的种类
     */
    private void chooseDrinkTypeWindow() {
        List<String> drinkList = new ArrayList<>();
        drinkList.add("白酒");
        drinkList.add("啤酒");
        drinkList.add("红酒");
        drinkList.add("黄酒");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "酒类", drinkList, object -> {
                    drinkName = drinkList.get(Integer.parseInt(String.valueOf(object)));
                    typeTv.setText(drinkName);
                    drinkType = drinkList.get(Integer.parseInt(String.valueOf(object)));

                }
        );
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
