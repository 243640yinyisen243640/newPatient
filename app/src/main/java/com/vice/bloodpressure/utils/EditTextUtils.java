package com.vice.bloodpressure.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

/**
 *
 * @date 2020/3/24
 */
public class EditTextUtils {
    /**
     * 限制小数点后面的位数
     *
     * @param et
     * @param decimalNum
     */
    public static void decimalNumber(final EditText et, final int decimalNum) {
        decimalNumber(et, decimalNum, Integer.MAX_VALUE);
    }

    /**
     * 限制小数点后面的位数和总的位数
     *
     * @param et         输入框
     * @param decimalNum 限制小数点后面的位数和总的位数
     * @param totalNum   总的位数
     */
    public static void decimalNumber(final EditText et, final int decimalNum, final int totalNum) {
        InputFilter lengthFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                // source:当前输入的字符
                // start:输入字符的开始位置
                // end:输入字符的结束位置
                // dest：当前已显示的内容
                // dstart:当前光标开始位置
                // dent:当前光标结束位置
                Log.i("yys", "start==" + start + "==end==" + end + "==dstart==" + dstart + "==dend" + dend);
                if (dest.length() == 0 && source.equals(".")) {
                    return "0.";
                }
                String dValue = dest.toString();
                if (dValue.length() >= totalNum) {
                    return "";
                }
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    String content = et.getText().toString().trim();
                    int index = content.indexOf(".");
                    if (dotValue.length() == decimalNum) {
                        if (index < dstart) {
                            return "";
                        }

                    }
                }
                return null;
            }
        };
        et.setFilters(new InputFilter[]{lengthFilter});
    }
}
