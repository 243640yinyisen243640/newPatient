package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.ProvinceInfo;
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
public class OutDataManager {
    /**
     * 获取省份
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getProvinceList(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, ProvinceInfo.class, "system/area/getAreaProvinceApp", map, successCallBack, failureCallBack);
    }
}
