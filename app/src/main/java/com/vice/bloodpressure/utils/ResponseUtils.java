package com.vice.bloodpressure.utils;

import android.content.Context;

import com.vice.bloodpressure.R;

import retrofit2.Call;


public class ResponseUtils {
    public static void defaultFailureCallBack(Context context, Call<String> call) {
        ToastUtils.getInstance().dismissProgressDialog();
        if (call == null) {
            ToastUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
        } else {
            if (!call.isCanceled()) {
                ToastUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
            }
        }
    }

    public static void defaultFailureCallBack(Context context, Call<String> call, Throwable throwable) {
        defaultFailureCallBack(context, call);
        //        XySoftTipUtils.getInstance().dismissProgressDialog();
        //        if (throwable != null && "401".equals(throwable.getMessage())) {
        //            if (call == null) {
        //                XySoftTipUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
        //            } else {
        //                if (!call.isCanceled()) {
        //                    XySoftTipUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
        //                }
        //            }
        //            //认证授权已过期，需要重新登录
        //            DialogUtils.showOptionDialogSure(context, context.getString(R.string.mall_authorization_expired_hint), (dialog, which) -> {
        //                dialog.dismiss();
        //                if (which == XySoftDialogActionEnum.POSITIVE) {
        //                    Intent intent = new Intent(context, LoginActivity.class);
        //                    intent.putExtra("mark", 1);
        //                    context.startActivity(intent);
        //                }
        //            });
        //        } else {
        //            if (call == null) {
        //                XySoftTipUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
        //            } else {
        //                if (!call.isCanceled()) {
        //                    XySoftTipUtils.getInstance().showToast(context, context.getString(R.string.huahansoft_net_error));
        //                }
        //            }
        //        }
    }
}
