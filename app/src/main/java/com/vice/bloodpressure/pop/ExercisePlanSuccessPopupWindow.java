package com.vice.bloodpressure.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;

public class ExercisePlanSuccessPopupWindow extends PopupWindow {
    private TextView contentTv;


    public ExercisePlanSuccessPopupWindow(Context context, View.OnClickListener recommendClickListener) {
        LinearLayout view = (LinearLayout) View.inflate(context, R.layout.pop_exercise_plan_success, null);
        contentTv = view.findViewById(R.id.tv_exercise_success_content);
        TextView sureTv = view.findViewById(R.id.tv_exercise_success_sure);

        sureTv.setOnClickListener(v -> {
            if (recommendClickListener != null) {
                recommendClickListener.onClick(v);
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

    public TextView showContent() {
        return contentTv;
    }
}
