package com.vice.bloodpressure.version;

import android.app.Activity;
import android.content.Context;

import androidx.core.content.ContextCompat;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.listener.ExceptionHandler;
import com.vice.bloodpressure.R;
import com.vice.bloodpressure.retrofit.BaseNetworkUtils;
import com.vice.bloodpressure.retrofit.BaseResponse;
import com.vice.bloodpressure.utils.AppUtils;
import com.vice.bloodpressure.utils.ResponseUtils;
import com.vice.bloodpressure.utils.ToastUtils;
import com.vice.bloodpressure.utils.TurnUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.BiConsumer;
import retrofit2.Call;


public class VersionUtils {
    private CheckVersionModel versionModel;

    private static VersionUtils mVersionUtils;

    public static VersionUtils getInstance() {
        if (mVersionUtils == null) {
            mVersionUtils = new VersionUtils();
        }
        return mVersionUtils;
    }

    public void updateNewVersion(Context context, Activity activity, final boolean isShow) {
        if (isShow) {
            ToastUtils.getInstance().showProgressDialog(context, R.string.waiting, false);
        }
        Call<String> requestCall = checkVersion((stringCall, response) -> {
            ToastUtils.getInstance().dismissProgressDialog();
            if ("0000".equals(response.code)) {
                versionModel = (CheckVersionModel) response.object;
                int oldCodeNum = AppUtils.appVersionCode(context);
                if (TurnUtils.getInt(versionModel.getVersionNum(), 0) > oldCodeNum) {
                    UpdateAppBean appBean = new UpdateAppBean();
                    appBean.setApkFileUrl(versionModel.getItunesUrl());
                    appBean.setConstraint("1".equals(versionModel.getIsMustUpdate()));
                    appBean.setOnlyWifi(false);
                    appBean.setUpdate("Yes");
                    appBean.setNewVersion(versionModel.getVersionNum());
                    appBean.setUpdateLog(versionModel.getUpdateContent());

                    updateApp(context, activity, appBean);
                } else {
                    if (isShow) {
                        ToastUtils.getInstance().showToast(context, R.string.new_last_version);
                    }
                }
            } else {
                if (isShow) {
                    ToastUtils.getInstance().showToast(context, response.msg);
                }
            }
        }, (stringCall, throwable) -> {
            if (isShow) {
                ResponseUtils.defaultFailureCallBack(context, stringCall);
            }
        });
    }

    /**
     * 版本更新
     *
     * @return
     */
    private Call<String> checkVersion(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failurCallBack) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("category", "1");
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, CheckVersionModel.class, "checksoftversion", paraMap, successCallBack, failurCallBack);
    }

    /**
     * 版本更新
     */
    public void updateApp(Context context, Activity activity, UpdateAppBean appBean) {
        new UpdateAppManager
                .Builder()
                //当前Activity
                .setActivity(activity)
                .dismissNotificationProgress()
                .setPost(false).dismissNotificationProgress()
                //更新地址
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        e.printStackTrace();
                    }
                })
                .setUpdateUrl(appBean.getApkFileUrl())
                //                .setTopPic(R.mipmap.top_8)
                .setIgnoreDefParams(true)
                .setThemeColor(ContextCompat.getColor(context, R.color.main_base_color))
                //实现httpManager接口的对象
                .setHttpManager(new UpdateAppHttpUtil())
                .build()
                .processData("", new UpdateCallback(), appBean);
    }


}
