package com.vice.bloodpressure.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MyFrameLayout extends FrameLayout {
    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        onTouchEvent(ev);
        return true;
    }
    //这个也是可以的  为什么要写这个类呢，就是因为我用的listview导致点击事件冲突了，
    //    @Override
    //    public boolean onInterceptTouchEvent(MotionEvent ev) {
    //        return true;
    //    }
}
