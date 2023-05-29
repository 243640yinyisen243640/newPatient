package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.model.UserInfo;
import com.vice.bloodpressure.retrofit.BaseNetworkUtils;
import com.vice.bloodpressure.retrofit.BaseResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.BiConsumer;
import retrofit2.Call;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserDataManager {
    /**
     * 我的医生
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSelectDoctorInfo(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DoctorInfo.class, "selectDoctorInfo", map, successCallBack, failureCallBack);
    }

    /**
     * 我的档案
     *
     * @param patientId
     * @param type
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getUserFilesInfo(String patientId, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "system/patient/detailApp", map, successCallBack, failureCallBack);
    }
}
