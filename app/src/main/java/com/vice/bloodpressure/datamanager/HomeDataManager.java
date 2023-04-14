package com.vice.bloodpressure.datamanager;

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
public class HomeDataManager {
    /**
     *
     * @param archivesId 档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDietPlan(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "ai/diet/v2/getDietPlanByArchivesId", map, successCallBack, failureCallBack);
    }
}
