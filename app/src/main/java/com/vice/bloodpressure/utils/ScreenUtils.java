package com.vice.bloodpressure.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * @类说明 XySoftScreenUtils
 * @作者
 * @创建日期 2019/8/19 18:13
 * 注意事项：
 * 一、屏幕显示区域以两种不同的方式描述：包括应用程序的显示区域和实际显示区域
 * 应用程序显示区域指定可能包含应用程序窗口的显示部分，不包括系统装饰。 应用程序显示区域可以小于实际显示区域，因为系统减去诸如状态栏之类的装饰元素所需的空间。使用以下方法查询应用程序显示区域：getSize（Point），getRectSize（Rect）和getMetrics（DisplayMetrics）。
 * 实际显示区域指定包含系统装饰的内容的显示部分。 即便如此，如果窗口管理器使用（adb shell wm size）模拟较小的显示器，则实际显示区域可能小于显示器的物理尺寸。使用以下方法查询实际显示区域：getRealSize（Point），getRealMetrics（DisplayMetrics）
 */
public class ScreenUtils {
    /**
     * 判断手机是否全屏
     *
     * @param context
     * @return
     */
    public static boolean isFullScreen(Context context) {
        return (((Activity) context).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 获取屏幕的宽（单位px）
     *
     * @param context
     * @return
     */
    public static int screenWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕实际的宽（单位px）
     *
     * @param context
     * @return
     */
    public static int realScreenWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高（单位px）
     *
     * @param context
     * @return
     */
    public static int screenHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕实际的高（单位px）
     *
     * @param context
     * @return
     */
    public static int realScreenHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    public static int statusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * dp2px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int getScreenWidth(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }
    public static int getScreenHeight(Context context) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels - getStatusBarHeight(context);
    }
    public static int getStatusBarHeight(Context context){
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

}
