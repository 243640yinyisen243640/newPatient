package com.vice.bloodpressure.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.core.content.ContextCompat;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.addresspickerlib.AddressPickerView;

public class ShowCityPopupWindow extends PopupWindow {


    public ShowCityPopupWindow(Context context, AddressPickerView.OnAddressPickerSureListener listener) {
        LinearLayout view = (LinearLayout) View.inflate(context, R.layout.pop_city_show, null);
        AddressPickerView pickerView = view.findViewById(R.id.pv_city_show);

        pickerView.setOnAddressPickerSure(listener);
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
