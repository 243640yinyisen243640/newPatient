package com.vice.bloodpressure.utils.tools;

import android.util.Log;

/**
 * @类说明 打印信息, 默认打印所有的信息
 * @作者
 * @创建日期 2019/8/18 17:44
 */
public class XyLogUtils {

    private static final int PRINT_LEVEL_I = 0;
    private static final int PRINT_LEVEL_W = 1;
    private static final int PRINT_LEVEL_E = 2;
    public static final int LEVEL_ALL = 3;
    public static final int LEVEL_NO = 0;
    public static final int LEVEL_I = 1;
    public static final int LEVEL_W = 2;
    public static final int LEVEL_E = 3;
    private static int PRINT_LEVEL = LEVEL_ALL;

    /**
     * 信息太长,分段打印
     *
     * @param tag
     * @param msg
     */
    public static void showLongLog(String tag, String msg) {
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }

    /**
     * 打印基本信息
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void i(String tag, String msg) {
        if (PRINT_LEVEL > PRINT_LEVEL_I) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印异常信息
     * 打印数据的形式为:header+"==="+e.getClass()+"=="+e.getMessage()
     *
     * @param header 头部标识
     * @param e      异常信息
     */
    public static void i(String tag, String header, Throwable e) {
        if (PRINT_LEVEL > PRINT_LEVEL_I) {
            Log.i(tag, header + "===" + e.getClass() + "==" + e.getMessage());
        }
    }

    /**
     * 打印警告信息
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void w(String tag, String msg) {
        if (PRINT_LEVEL > PRINT_LEVEL_W) {
            Log.w(tag, msg);
        }
    }

    /**
     * 打印异常信息
     * 打印数据的形式为:header+"==="+e.getClass()+"=="+e.getMessage()
     *
     * @param header 头部标识
     * @param e      异常信息
     */
    public static void w(String tag, String header, Throwable e) {
        if (PRINT_LEVEL > PRINT_LEVEL_W) {
            Log.w(tag, header + "===" + e.getClass() + "==" + e.getMessage());
        }
    }

    /**
     * 打印错误信息
     *
     * @param tag 标签
     * @param msg 消息
     */
    public static void e(String tag, String msg) {
        if (PRINT_LEVEL > PRINT_LEVEL_E) {
            Log.e(tag, msg);
        }
    }

    /**
     * 打印异常信息<br/>
     * 打印数据的形式为:header+"==="+e.getClass()+"=="+e.getMessage()
     *
     * @param header 头部标识
     * @param e      异常信息
     */
    public static void e(String tag, String header, Throwable e) {
        if (PRINT_LEVEL > PRINT_LEVEL_E) {
            Log.e(tag, header + "===" + e.getClass() + "==" + e.getMessage());
        }
    }

    /**
     * 设置打印信息的级别；<br/>
     * 可选值为：<br/>
     * LEVEL_I:打印基本的信息；<br/>
     * LEVEL_W:打印基本的信息和警告信息；<br/>
     * LEVEL_E:打印基本的信息和警告信息,错误信息；<br/>
     * LEVEL_ALL:打印所有信息<br/>
     * LEVEL_NO:不打印信息<br/>
     *
     * @param level
     */
    public static void setLogLevel(int level) {
        PRINT_LEVEL = level;
    }
}
