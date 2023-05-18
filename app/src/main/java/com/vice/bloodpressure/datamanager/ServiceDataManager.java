package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.BloodAllInfo;
import com.vice.bloodpressure.model.BloodThirdInfo;
import com.vice.bloodpressure.model.HealthyDataAllInfo;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
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
     *                        * # 8 糖化血红蛋白
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
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/home/saveMonitorBg", map, successCallBack, failureCallBack);
    }

    /**
     * APP血压数据统计
     *
     * @param archivesId
     * @param beginTime
     * @param endTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getPressureStatistic(String archivesId, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataAllInfo.class, "monitor/api/v2/htnApp/statistic", map, successCallBack, failureCallBack);
    }

    /**
     * 查询患者血压数据
     *
     * @param archivesId
     * @param pageNum
     * @param pageSize
     * @param beginTime
     * @param endTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getPressureList(String archivesId, String pageNum, String pageSize, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/htnApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param recordType      记录方式:1->自动记录;2->手动记录
     * @param sbp             收缩压
     * @param dbp             舒张压
     * @param hr              心率
     * @param addTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> insertMonitorHtn(String patientId, String recordType, String sbp, String dbp, String hr, String addTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("recordType", recordType);
        map.put("sbp", sbp);
        map.put("dbp", dbp);
        map.put("hr", hr);
        map.put("addTime", addTime);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/home/insertMonitorHtn", map, successCallBack, failureCallBack);
    }

    /**
     * @param archivesId
     * @param pageNum
     * @param pageSize
     * @param type            血红蛋白传9
     * @param beginTime
     * @param endTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getBloodScleroproteinList(String archivesId, String pageNum, String pageSize, String type, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("type", type);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataAllInfo.class, "monitor/api/v2/hbApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param type            数据类型:1->血压;2->BMI;3->体重;4->体温;5->血氧;6->心率;
     * @param recordType      记录方式:1->自动记录;2->手动记录
     * @param addTime
     * @param sbp             收缩压
     * @param dbp             舒张压
     * @param hr              静息心率
     * @param height          身高
     * @param weight          体重
     * @param bmi
     * @param spo             血氧
     * @param temp            体温
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> insertMonitorOther(String patientId, String type, String recordType,
                                                  String addTime, String sbp, String dbp, String hr, String height,
                                                  String weight, String bmi, String spo, String temp, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("type", type);
        map.put("recordType", recordType);
        map.put("addTime", addTime);
        map.put("sbp", sbp);
        map.put("dbp", dbp);
        map.put("hr", hr);
        map.put("height", height);
        map.put("weight", weight);
        map.put("bmi", bmi);
        map.put("spo", spo);
        map.put("temp", temp);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/home/insertMonitorOther", map, successCallBack, failureCallBack);
    }


    /**
     * APP血压数据统计
     *
     * @param archivesId
     * @param beginTime
     * @param endTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getBmiStatistic(String archivesId, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataAllInfo.class, "monitor/api/v2/otherApp/statistic", map, successCallBack, failureCallBack);
    }

    /**
     * @param archivesId
     * @param pageNum
     * @param pageSize
     * @param beginTime
     * @param endTime
     * @param type            数据类型:1->血压;2->BMI;3->体重;4->体温;5->血氧;6->心率;
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getBmiList(String archivesId, String pageNum, String pageSize, String beginTime, String endTime, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("type", type);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/otherApp/list", map, successCallBack, failureCallBack);
    }

}
