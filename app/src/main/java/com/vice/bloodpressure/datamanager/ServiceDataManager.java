package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.BloodAllInfo;
import com.vice.bloodpressure.model.BloodThirdInfo;
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
public class ServiceDataManager {
    /**
     * @param beginTime       开始时间
     * @param endTime         结束时间
     * @param dateType        1：7天 ，2 ：30天
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getExceptionData(String beginTime, String endTime, String dateType, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("dateType", dateType);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, BloodAllInfo.class, "monitor/api/v2/bgApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 多条血糖数据
     *
     * @param beginTime
     * @param type
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> dateDetail(String beginTime, String type, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("beginTime", beginTime);
        map.put("type", type);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, BloodThirdInfo.class, "monitor/api/v2/bgApp/dateDetail", map, successCallBack, failureCallBack);
    }

    /**
     * 查看血糖控制目标
     *
     * @param patientId
     * @param timePoint
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> bgTargetByType(String patientId, String timePoint, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("timePoint", timePoint);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, BloodThirdInfo.class, "monitor/api/v2/bgTargetByType", map, successCallBack, failureCallBack);
    }

    /**
     * 新增患者血糖数据
     *
     * @param patientId
     * @param type            * 1 空腹
     *                        * # 2 早餐后
     *                        * # 3 午餐前
     *                        * # 4 午餐后
     *                        * # 5 晚餐前
     *                        * # 6 晚餐后
     *                        * # 7 睡前
     *                        * # 8 凌晨
     * @param recordType      记录方式:1->自动记录;2->手动记录
     * @param addTime         添加时间
     * @param bgValue         值
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> saveMonitorBg(String patientId, String type, String recordType, String addTime, String bgValue, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("type", type);
        map.put("recordType", recordType);
        map.put("addTime", addTime);
        map.put("bgValue", bgValue);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_ARRAY, BloodThirdInfo.class, "monitor/home/saveMonitorBg", map, successCallBack, failureCallBack);
    }
}
