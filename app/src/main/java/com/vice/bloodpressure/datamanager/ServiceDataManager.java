package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.BloodAllInfo;
import com.vice.bloodpressure.model.BloodThirdInfo;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.model.GalleryInfo;
import com.vice.bloodpressure.model.HealthyDataAllInfo;
import com.vice.bloodpressure.model.HealthyDataChildInfo;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.retrofit.BaseNetworkUtils;
import com.vice.bloodpressure.retrofit.BaseResponse;
import com.vice.bloodpressure.retrofit.HHSoftNetworkUtils;
import com.vice.bloodpressure.view.image.GalleryUploadImageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.BiConsumer;
import okhttp3.MultipartBody;
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

    /**
     * @param archivesId
     * @param pageNum
     * @param pageSize
     * @param beginTime
     * @param endTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getExerciseList(String archivesId, String pageNum, String pageSize, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/sportApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param recordType      记录方式:1->自动记录;2->手动记录
     * @param addTime         添加时间
     * @param sportName       运动名称
     * @param sportTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> saveMonitorSport(String patientId, String recordType, String addTime, String sportName, String sportTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("recordType", recordType);
        map.put("addTime", addTime);
        map.put("sportName", sportName);
        map.put("sportTime", sportTime);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/home/saveMonitorSport", map, successCallBack, failureCallBack);
    }

    /**
     * 用药记录列表
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
    public static Call<String> getMedicineRecordList(String archivesId, String pageNum, String pageSize, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/drugApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 添加更新用药记录
     *
     * @param patientId
     * @param pkId            更新的时候必填
     * @param recordType      记录方式:1->自动记录;2->手动记录
     * @param drugName        药品名称
     * @param drugSpec        药品规格
     * @param drugSpecUnit    药品规格单位
     * @param drugTimes       用药次数
     * @param drugDose        用药剂量
     * @param drugUnit        用药单位
     * @param
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> medicineAdd(String patientId, String pkId, String recordType, String drugName,
                                           String drugSpec, String drugSpecUnit, String drugTimes, String drugDose, String drugUnit,
                                           String addTime, String finishTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("pkId", pkId);
        map.put("recordType", recordType);
        map.put("drugName", drugName);
        map.put("drugTimes", drugTimes);
        map.put("drugDose", drugDose);
        map.put("drugUnit", drugUnit);
        map.put("addTime", addTime);
        map.put("finishTime", finishTime);
        map.put("drugSpec", drugSpec);
        map.put("drugSpecUnit", drugSpecUnit);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/drugApp/add", map, successCallBack, failureCallBack);
    }

    /**
     * 删除用药记录
     *
     * @param pkId            药品ID
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> medicineDelete(String pkId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        return BaseNetworkUtils.deleteRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/drugApp/delete", map, successCallBack, failureCallBack);
    }

    /**
     * 用药记录查看
     *
     * @param pkId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> medicineLook(String pkId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataChildInfo.class, "monitor/api/v2/drugApp/select", map, successCallBack, failureCallBack);
    }

    /**
     * 用药提醒列表
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
    public static Call<String> getDrugWarnAppList(String archivesId, String pageNum, String pageSize, String beginTime, String endTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/drugWarnApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 删除用药提醒
     *
     * @param pkId            药品ID
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> drugWarnAppDelete(String pkId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        return BaseNetworkUtils.deleteRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/drugWarnApp/delete", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param pkId
     * @param recordType
     * @param drugName
     * @param drugSpec        药品规格
     * @param drugSpecUnit    药品规格单位
     * @param drugNumber      药品数量
     * @param drugMode        药品用法(1饭前服,2饭后服,3舌下含服,4口服,5水煎服,6露化吸乳,7喉咙,8静滴,9肌注,10嚼服,11冲服,12外用,13外敷,14外洗,15皮下注射)
     * @param drugTimes       用药次数(日)
     * @param drugDose        用药剂量
     * @param drugDoseUnit    用药剂量单位
     * @param wranTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> drugWarnAppAdd(String patientId, String pkId, String recordType, String drugName,
                                              String drugSpec, String drugSpecUnit, String drugNumber, String drugMode,
                                              String drugTimes, String drugDose, String drugDoseUnit,
                                              String wranTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("pkId", pkId);
        map.put("recordType", recordType);
        map.put("drugName", drugName);
        map.put("drugSpec", drugSpec);
        map.put("drugSpecUnit", drugSpecUnit);
        map.put("drugNumber", drugNumber);
        map.put("drugMode", drugMode);
        map.put("drugTimes", drugTimes);
        map.put("drugDose", drugDose);
        map.put("drugDoseUnit", drugDoseUnit);
        map.put("wranTime", wranTime);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/drugWarnApp/add", map, successCallBack, failureCallBack);
    }

    /**
     * 查看药品提醒
     *
     * @param pkId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> drugWarnAppLook(String pkId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataChildInfo.class, "monitor/api/v2/drugWarnApp/select", map, successCallBack, failureCallBack);
    }

    /**
     * 获取食物列表
     *
     * @param patientId
     * @param pageNum
     * @param pageSize
     * @param beginTime
     * @param endTime
     * @param type
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getMealList(String patientId, String pageNum, String pageSize, String beginTime, String endTime, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("type", type);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataAllInfo.class, "monitor/api/v2/dietApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 添加饮食数据
     *
     * @param patientId
     * @param pkId
     * @param recordType
     * @param eatPoint
     * @param addTime
     * @param foodName
     * @param foodBigCards
     * @param foodWeight
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> mealAdd(String patientId, String pkId, String recordType, String eatPoint, String addTime,
                                       String foodName, String foodBigCards, String foodWeight, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("pkId", pkId);
        map.put("recordType", recordType);
        map.put("addTime", addTime);
        map.put("eatPoint", eatPoint);
        map.put("foodName", foodName);
        map.put("foodBigCards", foodBigCards);
        map.put("foodWeight", foodWeight);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/dietApp/saveOrUpdate", map, successCallBack, failureCallBack);
    }

    public static Call<String> getMealType(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MealChildInfo.class, "system/food/classify/list", map, successCallBack, failureCallBack);
    }

    public static Call<String> getMealTypeList(String classify, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("classify", classify);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MealChildInfo.class, "system/food/library/list", map, successCallBack, failureCallBack);
    }

    /**
     * 上传多图
     *
     * @param imagesList
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userUploadImgMultipleSheets(List<GalleryUploadImageInfo> imagesList, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("bucketName", "inspect");
        List<MultipartBody.Part> files = new ArrayList<>();
        if (imagesList != null && imagesList.size() > 0) {
            for (int i = 0; i < imagesList.size(); i++) {
                files.add(HHSoftNetworkUtils.toFileMultipartBodyPart("files", imagesList.get(i).getThumbImage()));
            }
        }
        return BaseNetworkUtils.uploadImgRequest(false, BaseNetworkUtils.JSON_OBJECT, GalleryInfo.class, "file/upload/v2", map, files, successCallBack, failureCallBack);
    }

    /**
     * 获取检验检查列表
     *
     * @param patientId
     * @param pageNum
     * @param pageSize
     * @param beginTime
     * @param endTime
     * @param type
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getCheckList(String patientId, String pageNum, String pageSize, String beginTime, String endTime, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("beginTime", beginTime);
        map.put("endTime", endTime);
        map.put("type", type);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, HealthyDataChildInfo.class, "monitor/api/v2/inspectApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 检验检查添加
     *
     * @param patientId
     * @param recordType
     * @param projectName
     * @param fileItem
     * @param addTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> checkAdd(String patientId, String recordType, String projectName, String fileItem,
                                        String addTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("recordType", recordType);
        map.put("projectName", projectName);
        map.put("fileItem", fileItem);
        map.put("addTime", addTime);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/inspectApp/add", map, successCallBack, failureCallBack);
    }

    /**
     * 检验检查查看
     *
     * @param pkId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> checkLook(String pkId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HealthyDataChildInfo.class, "monitor/api/v2/inspectApp/select", map, successCallBack, failureCallBack);
    }


    /**
     * @param cid
     * @param pageNum
     * @param pageSize
     * @param dishName
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getMealVideoList(String cid, String pageNum, String pageSize, String dishName, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("dishName", dishName);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MealExclusiveInfo.class, "/ai/diet/v2/list", map, successCallBack, failureCallBack);
    }

    /**
     * 教育视频列表
     * @param searchKey
     * @param pageNum
     * @param pageSize
     * @param videoType
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getEduVideoPage(String searchKey, String pageNum, String pageSize, String videoType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("searchKey", searchKey);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("videoType", videoType);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, EducationInfo.class, "ai/edu/v2/getEduVideoPage", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param id              患者ID
     * @param collectType     收藏模块  1教育模块  2.饮食模块
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> mealDetails(String patientId, String id, String collectType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("collectType", collectType);
        map.put("id", id);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, MealExclusiveInfo.class, "ai/diet/v2/detail", map, successCallBack, failureCallBack);
    }

    /**
     * 收藏
     *
     * @param patientId
     * @param collectIds
     * @param collectType     1，智能教育文章；2，教育视频；3，饮食模块
     * @param isCollect       1收藏/2取消收藏
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> mealCollectOperate(String patientId, String collectIds, String collectType, String isCollect, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("collectIds", collectIds);
        map.put("collectType", collectType);
        map.put("isCollect", isCollect);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/patient/collect/addOrUpdate", map, successCallBack, failureCallBack);
    }
}
