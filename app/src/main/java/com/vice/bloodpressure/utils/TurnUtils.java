package com.vice.bloodpressure.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;

public class TurnUtils {
    /**
     * @param content
     * @param defaultValue 默认值
     * @return
     */
    public static int getInt(String content, int defaultValue) {


        try {
            return Integer.valueOf(content);
        } catch (Exception e) {

            return defaultValue;
        }

    }

    /**
     * @param content
     * @param defaultValue
     * @return
     */
    public static float getFloat(String content, float defaultValue) {
        try {
            return Float.valueOf(content);
        } catch (Exception e) {

            return defaultValue;
        }

    }

    /**
     * @param content
     * @param defaultValue 当输出内容为null 时需要返回的值
     * @return
     */
    public static double getDouble(String content, double defaultValue) {

        try {
            return Double.valueOf(content);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    /**
     * @param content
     * @param defaultValue 当输出内容为null 时需要返回的值
     * @return
     */
    public static long getLong(String content, long defaultValue) {

        try {
            return Long.valueOf(content);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    /**
     * 小数位数补0
     *
     * @param data
     * @param decimalCount
     * @return
     */
    public static String setDecimalCount(double data, int decimalCount) {
        String formatStr = "0.0";
        for (int i = 1; i < decimalCount; i++) {
            formatStr += "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(formatStr);
        String dataStr = decimalFormat.format(data);
        return dataStr;
    }
    /**
     * 小数位数补0
     *
     * @param data
     * @param decimalCount
     * @return
     */
    public static String setDecimalCount(String data, int decimalCount) {
        String formatStr = "0.0";
        for (int i = 1; i < decimalCount; i++) {
            formatStr += "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(formatStr);
        String dataStr = decimalFormat.format(data);
        return dataStr;
    }
    /**
     * 小数位数补0
     *
     * @param data
     * @param decimalCount
     * @return
     */
    public static String setDecimalCount(Float data, int decimalCount) {
        String formatStr = "0.0";
        for (int i = 1; i < decimalCount; i++) {
            formatStr += "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(formatStr);
        String dataStr = decimalFormat.format(data);
        return dataStr;
    }

    /**
     * 将每三个数字加上逗号处理,最后保留两位小数（通常使用金额方面的编辑）示例：9，702.44
     *
     * @param str
     * @return
     */
    public static String addCommaDots(String str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(",##0.00");
        return myformat.format(Double.parseDouble(str));
    }


    /**
     * 将每三个数字加上逗号处理 不保留小数（通常使用金额方面的编辑）示例：9，702
     *
     * @param str
     * @return
     */
    public static String addCommaDotsNoDecimal(String str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern(",##0");
        return myformat.format(Double.parseDouble(str));
    }

    /**
     * 判断手机格式
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "^((1[3,5,6,7,8,9][0-9]))\\d{8}$";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }
}
