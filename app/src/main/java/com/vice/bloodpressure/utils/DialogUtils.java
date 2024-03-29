package com.vice.bloodpressure.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.vice.bloodpressure.R;
import com.vice.bloodpressure.dialog.XySoftDialog;


/**
 * 类描述：
 * 类传参：
 *
 * @author android.zsj
 * @date 2019/10/18
 */
public class DialogUtils {

    /**
     * 本项目使用
     *
     * @param context
     * @param tip      不传，不现实title
     * @param msg
     * @param negative 左边文字
     * @param positive 右边文字
     * @param callback
     */
    public static void showOperDialog(Context context, String tip, String msg, String negative, String positive, CommonDialog.SingleButtonCallback callback) {
        CommonDialog commonDialog = new CommonDialog(context);
        commonDialog.setTitle(R.string.tip);
        commonDialog.setMessage(msg);
        commonDialog.setNegative(negative);
        commonDialog.setPositive(positive);
        commonDialog.onAny(callback);
        commonDialog.show();
    }

    /**
     * 本项目使用
     *
     * @param context
     * @param tip      不传，不现实title
     * @param msg
     * @param negative 左边文字
     * @param positive 右边文字
     * @param callback
     */
    public static void showOperDialog(Context context, String tip, String msg, String negative, String positive, boolean isShowBg, CommonDialog.SingleButtonCallback callback) {
        CommonDialog commonDialog = new CommonDialog(context);
        commonDialog.setTitle(R.string.tip);
        commonDialog.setMessage(msg);
        commonDialog.setNegative(negative);
        commonDialog.setPositive(positive);
        commonDialog.setShowBg(isShowBg);
        commonDialog.onAny(callback);
        commonDialog.show();
    }

    /**
     * 选项dialog,取消和确定
     *
     * @param context
     * @param msg      内容
     * @param callback 回调
     */
    public static void showOptionDialog(Context context, String msg, XySoftDialog.SingleButtonCallback callback) {
        new XySoftDialog.Builder(context)
                .content(msg)
                .negativeColor(R.color.gray_98)
                .positiveColor(R.color.main_base_color)
                .negativeText(R.string.cancel)
                .positiveText(R.string.sure)
                .onAny(callback)
                .show();
    }

    /**
     * 提示dialog,只有确定按钮
     *
     * @param context
     * @param msg
     * @param callback
     */
    public static void showTipDialog(Context context, String msg, XySoftDialog.SingleButtonCallback callback) {
        new XySoftDialog.Builder(context)
                .title(R.string.tip)
                .content(msg)
                .positiveColorRes(R.color.main_base_color)
                .positiveText(R.string.sure)
                .onAny(callback)
                .show();

    }

    /**
     * 权限请求被拒是提示
     *
     * @param context
     * @param msg
     * @param callback
     */
    public static void showPermissionsDeniedDialog(Context context, String msg, XySoftDialog.SingleButtonCallback callback) {
        new XySoftDialog.Builder(context).content(msg).contentColorRes(R.color.text_white).negativeColorRes(R.color.grayAB).negativeText(R.string.cancel).positiveColorRes(R.color.main_base_color).positiveText(R.string.sure).cancelable(false).onPositive((xySoftDialog, xySoftDialogActionEnum) -> {
            xySoftDialog.dismiss();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent);
        }).onNegative(callback).show();
    }
    //    public static void showOperDialog(Context context, int resID, final HHDialogOperListener listener) {
    //        HHDialogUtils.BuilderOper builder = new HHDialogUtils.BuilderOper(context);
    //        // 0:资源resID; 1:string[]; 2:List<String>
    //        builder.setSourcesType(0);
    //        builder.setResID(resID);
    //        //builder.setResArray(resID);
    //        //builder.setResList(resID);
    //        builder.setDpMargin(30);
    //        builder.setDpPadding(12);
    //        builder.setTextSize(16);
    //        builder.setDividerColor(R.color.color_f0);
    //        builder.setTextColor(R.color.main_base_color);
    //        builder.setItemClickListener(listener);
    //        builder.createOperDialog().show();
    //    }
    //
    //    public static void showOperDialog(Context context, List<String> list, final HHDialogOperListener listener) {
    //        HHDialogUtils.BuilderOper builder = new HHDialogUtils.BuilderOper(context);
    //        // 0:资源resID; 1:string[]; 2:List<String>
    //        builder.setSourcesType(2);
    //        // builder.setResID(resID);
    ////        builder.setResArray(resID);
    //        builder.setResList(list);
    //        builder.setDpMargin(30);
    //        builder.setDpPadding(12);
    //        builder.setTextSize(16);
    //        builder.setDividerColor(R.color.color_f0);
    //        builder.setTextColor(R.color.main_base_color);
    //        builder.setItemClickListener(listener);
    //        builder.createOperDialog().show();
    //    }
    //
    //    public static void showOperDialogType3(Context context, List<? extends HHBaseTextImp> list, final HHDialogOperListener listener) {
    //        HHDialogUtils.BuilderOper builder = new HHDialogUtils.BuilderOper(context);
    //        // 0:资源resID; 1:string[]; 2:List<String>
    //        builder.setSourcesType(3);
    //        // builder.setResID(resID);
    //        //builder.setResArray(resID);
    //        // builder.setResList();
    //        builder.setResListBaseTextImp(list);
    //        builder.setDpMargin(30);
    //        builder.setDpPadding(12);
    //        builder.setTextSize(16);
    //        builder.setDividerColor(R.color.color_f0);
    //        builder.setTextColor(R.color.main_base_color);
    //        builder.setItemClickListener(listener);
    //        builder.createOperDialog().show();
    //    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                         朋友圈评论弹出框开始
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //    public static void showMomentsCommentDialogFragment(FragmentManager fragmentManager, CommentDialogFragment.ICommentDialogListener listener) {
    //        showMomentsCommentDialogFragment(fragmentManager, "", listener);
    //    }
    //
    //    public static void showMomentsCommentDialogFragment(FragmentManager fragmentManager, String hint, CommentDialogFragment.ICommentDialogListener listener) {
    //        Bundle bundle = new Bundle();
    //        bundle.putString("hint", hint);
    //        CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance()
    //                .setArgument(bundle)
    //                .setOnCommentDialogListener(listener);
    //        commentDialogFragment.show(fragmentManager, "moments");
    //    }
    //
    //    public static void closeMomentsCommentDialogFragment() {
    //        CommentDialogFragment.newInstance().dismiss();
    //    }


}
