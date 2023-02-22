package com.vice.bloodpressure.utils;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.baseimp.CallBack;

import java.util.Calendar;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class PickerViewUtils {
    /**
     * @param context
     * @param booleans
     * @param dataManager
     * @param callBack
     */
    public static void showTimeWindow(Context context, boolean[] booleans, String dataManager, CallBack callBack) {
        Calendar currentDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        startDate.set(currentYear - 120, 0, 1, 0, 0);

        TimePickerView timePickerView = new TimePickerBuilder(context, (date, v) -> {
            String content = DataUtils.convertDateToString(date, dataManager);
            callBack.callBack(content);
        })
                .setDate(currentDate)
                .setRangDate(startDate, endDate)
                .setType(booleans)
                .setSubmitColor(ContextCompat.getColor(context, R.color.main_base_color))
                .setCancelColor(ContextCompat.getColor(context, R.color.gray_98))
                .build();
        timePickerView.show();
    }

    /**
     * @param context
     * @param booleans
     * @param dataManager
     * @param parentFra
     * @param callBack
     */
    public static void showTimeWindow(Context context, boolean[] booleans, String dataManager, FrameLayout parentFra, CallBack callBack) {
        Calendar currentDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentHour = currentDate.get(Calendar.HOUR);
        startDate.set(currentYear - 120, 0, 1, 0, 0);
        TimePickerView timePickerView = new TimePickerBuilder(context, (date, v) -> {
            String content = DataUtils.convertDateToString(date, dataManager);
            callBack.callBack(content);
        }).setDate(currentDate).setRangDate(startDate, endDate)
                .setType(booleans)
                .setSubmitColor(ContextCompat.getColor(context, R.color.main_base_color))
                .setCancelColor(ContextCompat.getColor(context, R.color.gray_98))
                //                .isDialog(true)
                .setDecorView(parentFra)
                .build();
        //        //设置dialog弹出位置
        //        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        //        params.leftMargin = 0;
        //        params.rightMargin = 0;
        //        ViewGroup contentContainer = timePickerView.getDialogContainerLayout();
        //        contentContainer.setLayoutParams(params);
        //        timePickerView.getDialog().getWindow().setGravity(Gravity.BOTTOM);//可以改成Bottom
        //        timePickerView.getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        timePickerView.show();

    }

    /**
     *
     * @param context
     * @param title
     * @param list
     * @param callBack
     */
    public static <T> void showChooseSinglePicker(Context context, String title, List<T> list, CallBack callBack) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(context, (options1, options2, options3, v) -> {

            callBack.callBack(options1);
        }).setLineSpacingMultiplier(1.5f)
                .setCancelColor(ContextCompat.getColor(context, R.color.gray_85))
                .setSubmitColor(ContextCompat.getColor(context, R.color.main_base_color))
                .setTitleText(title)
                .setTitleBgColor(ContextCompat.getColor(context, R.color.text_white))
                .setDividerColor(ContextCompat.getColor(context, R.color.gray_EA))
                .build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }


}
