package com.vice.bloodpressure.activity.auser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
 * 传参:type  1：抽烟  2：喝酒
 * 描述:抽烟喝酒的编辑数量
 */
public class UserSmokeDrinkActivity extends UIBaseActivity {

    private TextView titleTextView;
    private CheckBox yesCb;
    private CheckBox noCb;
    private LinearLayout smokeLinerLayout;
    private EditText smokeNumEt;
    private TextView typeTv;
    private EditText drinkNumEt;
    private LinearLayout drinkLinerLayout;
    private TextView sureTv;
    /**
     * 1：抽烟  2：喝酒
     */
    private String type;

    private String drinkType = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("type");
        initView();
        initListener();
    }

    private void initListener() {
        yesCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                noCb.setChecked(false);
            } else {
                noCb.setChecked(true);
            }
        });
        noCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                yesCb.setChecked(false);
            } else {
                yesCb.setChecked(true);
            }
        });

        typeTv.setOnClickListener(v -> {
            chooseDrinkTypeWindow();
        });
        sureTv.setOnClickListener(v -> {
            Intent intent = new Intent();
            if ("1".equals(type)) {
                intent.putExtra("isCheck", yesCb.isChecked() ? "1" : "0");
                intent.putExtra("smokeNum", smokeNumEt.getText().toString().trim());
            } else {
                intent.putExtra("isCheck", yesCb.isChecked() ? "1" : "0");
                intent.putExtra("drinkNum", drinkNumEt.getText().toString().trim());
                intent.putExtra("drinkType", drinkType);
            }
            setResult(RESULT_OK, intent);
            finish();
        });

    }


    /**
     * 酒的种类
     */
    private void chooseDrinkTypeWindow() {
        List<String> exerciseList = new ArrayList<>();
        exerciseList.add("白酒");
        exerciseList.add("啤酒");
        exerciseList.add("红酒");
        exerciseList.add("黄酒");

        PickerViewUtils.showChooseSinglePicker(getPageContext(), "酒类", exerciseList, object -> {
                    typeTv.setText(exerciseList.get(Integer.parseInt(String.valueOf(object))));
                    drinkType = exerciseList.get(Integer.parseInt(String.valueOf(object)));
                }
        );
    }

    private void initView() {
        View view = View.inflate(getPageContext(), R.layout.activity_user_smoke_drink, null);
        containerView().addView(view);
        titleTextView = view.findViewById(R.id.tv_files_smoke_drink_title);
        yesCb = view.findViewById(R.id.cb_files_smoke_drink_yes);
        noCb = view.findViewById(R.id.cb_files_smoke_drink_no);
        smokeNumEt = view.findViewById(R.id.et_files_smoke_drink_smoke_num);
        smokeLinerLayout = view.findViewById(R.id.ll_files_smoke);
        typeTv = view.findViewById(R.id.tv_files_smoke_drink_type);
        drinkNumEt = view.findViewById(R.id.et_files_smoke_drink_num);
        drinkLinerLayout = view.findViewById(R.id.ll_files_drink);
        sureTv = view.findViewById(R.id.tv_files_smoke_drink_sure);
        if ("1".equals(type)) {
            titleTextView.setText("是否吸烟");
            smokeLinerLayout.setVisibility(View.VISIBLE);
            drinkLinerLayout.setVisibility(View.GONE);
        } else {
            titleTextView.setText("是否饮酒");
            smokeLinerLayout.setVisibility(View.GONE);
            drinkLinerLayout.setVisibility(View.VISIBLE);
        }
    }

}
