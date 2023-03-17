package com.vice.bloodpressure.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;

public class AnswerForPopupWindow extends PopupWindow {

    public AnswerForPopupWindow(Context context, String type, View.OnClickListener otherClickListener, View.OnClickListener selfClickListener) {
        LinearLayout view = (LinearLayout) View.inflate(context, R.layout.pop_answer_for, null);
        ImageView bgImageView = view.findViewById(R.id.iv_answer_for_bg);
        ImageView closeImageView = view.findViewById(R.id.iv_answer_for_close);
        TextView titleTextView = view.findViewById(R.id.tv_answer_for_title);
        TextView tvOther = view.findViewById(R.id.tv_answer_for_other);
        TextView tvSelf = view.findViewById(R.id.tv_answer_for_self);
        if ("1".equals(type)) {
            bgImageView.setImageResource(R.drawable.answer_for_bg);
            titleTextView.setText("请选择饮食方案对象");
        }else {
            bgImageView.setImageResource(R.drawable.answer_for_exercise);
            titleTextView.setText("PingFang-SC-Bold");
        }
        closeImageView.setOnClickListener(v -> {
            dismiss();
        });

        tvOther.setOnClickListener(v -> {
            if (otherClickListener != null) {
                otherClickListener.onClick(v);
            }
        });
        tvSelf.setOnClickListener(v -> {
            if (selfClickListener != null) {
                selfClickListener.onClick(v);
            }
        });
        //解决PopupWindow无法覆盖状态栏
        this.setClippingEnabled(false);
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(FrameLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.HuaHanSoft_Window_Fade_Anim);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(context, R.color.transparent));
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //因为某些机型是虚拟按键的,所以要加上以下设置防止挡住按键.
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        view.setOnClickListener(v -> dismiss());
    }
}
