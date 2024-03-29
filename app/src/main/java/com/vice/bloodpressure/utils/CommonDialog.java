package com.vice.bloodpressure.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.dialog.XySoftDialogActionEnum;


public class CommonDialog extends Dialog {
    private TextView tvTip;
    private TextView tvMsg;
    private TextView tvNegative;
    private TextView tvPositive;
    private TextView tvBg;

    private String tip;
    private String msg;
    private String negative;
    private String positive;
    private boolean isShowBg = false;
    private SingleButtonCallback callback;
    private Context context;

    public CommonDialog(@NonNull Context context) {
        super(context, R.style.custom_dialog2);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_oper, null);
        setContentView(view, new ViewGroup.LayoutParams(ScreenUtils.screenWidth(context) - DensityUtils.dip2px(context, 80), ViewGroup.LayoutParams.WRAP_CONTENT));
        setCanceledOnTouchOutside(false);
        initView();
        refreshView();
    }

    @Override
    public void show() {
        super.show();
        refreshView();
    }

    /**
     * 初始化界面控件的显示数据
     */
    private void refreshView() {
        //如果用户自定了title和message
        if (!TextUtils.isEmpty(tip)) {
            tvTip.setText(R.string.tip);
        }
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
        //如果设置按钮的文字
        if (!TextUtils.isEmpty(positive)) {
            tvPositive.setText(positive);
        } else {
            tvPositive.setText(R.string.sure);
        }
        if (!TextUtils.isEmpty(negative)) {
            tvNegative.setText(negative);
        } else {
            tvNegative.setText(R.string.cancel);
        }

        if (isShowBg) {
            tvBg.setVisibility(View.VISIBLE);
        } else {
            tvBg.setVisibility(View.GONE);
        }
    }

    private void initView() {
        tvTip = findViewById(R.id.tv_oper_hint);
        tvMsg = findViewById(R.id.tv_oper_content);
        tvNegative = findViewById(R.id.tv_oper_cancel);
        tvPositive = findViewById(R.id.tv_oper_sure);
        tvBg = findViewById(R.id.tv_oper_bg);
        tvNegative.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClick(this, XySoftDialogActionEnum.NEGATIVE);
            }
        });
        tvPositive.setOnClickListener(v -> {
            if (callback != null) {
                callback.onClick(this, XySoftDialogActionEnum.POSITIVE);
            }
        });
    }

    public String getMessage() {
        return msg;
    }

    public CommonDialog setMessage(String message) {
        this.msg = message;
        return this;
    }

    public String getTitle() {
        return tip;
    }

    public CommonDialog setTitle(String title) {
        this.tip = title;
        return this;
    }

    public String getPositive() {
        return positive;
    }

    public CommonDialog setPositive(String positive) {
        this.positive = positive;
        return this;
    }

    public CommonDialog setShowBg(boolean isShowBg) {
        this.isShowBg = isShowBg;
        return this;
    }

    public String getNegative() {
        return negative;
    }

    public CommonDialog setNegative(String negative) {
        this.negative = negative;
        return this;
    }

    public CommonDialog onAny(SingleButtonCallback callback) {
        this.callback = callback;
        return this;
    }

    public interface SingleButtonCallback {
        void onClick(CommonDialog dialog, XySoftDialogActionEnum which);
    }
}
