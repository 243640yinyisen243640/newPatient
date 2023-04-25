package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.model.ExerciseInfo;
import com.vice.bloodpressure.model.HomeAllInfo;
import com.vice.bloodpressure.model.MealInfo;
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
     * 首页
     *
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getHomeData(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HomeAllInfo.class, "app/v2/home", map, successCallBack, failureCallBack);
    }


    /**
     * 智能饮食
     *
     * @param archivesId      档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDietPlan(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, MealInfo.class, "ai/diet/v2/getDietPlanByArchivesId", map, successCallBack, failureCallBack);
    }


    /**
     * 饮食答题
     *
     * @param height          身高(cm)
     * @param weight          体重(kg)
     * @param dkd             肾病
     *                        0->无
     *                        1->1期
     *                        2->2期
     *                        3->三期及以上
     * @param professionType  职业类型
     *                        1->轻体力
     *                        2->中体力
     *                        3->重体力
     *                        4->卧床
     * @param archivesId      档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> recommendDietPlan(String height, String weight, String dkd, String professionType, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("height", height);
        map.put("weight", weight);
        map.put("dkd", dkd);
        map.put("professionType", professionType);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/diet/v2/recommendDietPlan", map, successCallBack, failureCallBack);
    }

    /**
     * 运动答题
     *
     * @param height
     * @param weight
     * @param diseases           疾病 （逗号拼接）
     *                           chd -> 冠心病
     *                           dn -> 肾病
     *                           dr -> 视网膜病变
     *                           dpn -> 神经病变
     *                           htn -> 高血压
     *                           no -> 无
     * @param whetherSportHabits 是否有运动习惯 Y N
     * @param whetherEmptySport  是否空腹运动 Y N
     * @param exerciseTime       运动时间
     * @param exerciseFrequency  运动频次
     * @param archivesId         档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> recommendSportPlan(String archivesId, String height, String weight, String diseases,
                                                  String whetherSportHabits, String whetherEmptySport, String exerciseTime, String exerciseFrequency, String age, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("height", height);
        map.put("weight", weight);
        map.put("diseases", diseases);
        map.put("whetherSportHabits", whetherSportHabits);
        map.put("whetherEmptySport", whetherEmptySport);
        map.put("exerciseTime", exerciseTime);
        map.put("exerciseFrequency", exerciseFrequency);
        map.put("age", age);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_OBJECT, ExerciseInfo.class, "ai/sport/v2/recommendSportPlan", map, successCallBack, failureCallBack);
    }

    /**
     * 根据档案号获取运动方案
     *
     * @param archivesId      档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSportPlan(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, ExerciseInfo.class, "ai/sport/v2/getSportPlan", map, successCallBack, failureCallBack);
    }

    /**
     * 添加有氧运动记录
     *
     * @param aerobicsId      有氧运动id
     * @param workouts        运动时长(分钟)
     * @param calories        热量
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */

    public static Call<String> addAerobicsRecord(String aerobicsId, String workouts, String calories, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("aerobicsId", aerobicsId);
        map.put("workouts", workouts);
        map.put("calories", calories);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/sport/v2/addAerobicsRecord", map, successCallBack, failureCallBack);
    }

    /**
     * 添加抗阻/柔韧性运动记录
     *
     * @param sportId
     * @param sportNumber     运动数量
     * @param type            P 柔韧性运动
     *                        R 抗阻运动
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> addPliableResistanceRecord(String sportId, String sportNumber, String type, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("sportId", sportId);
        map.put("sportNumber", sportNumber);
        map.put("type", type);
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/sport/v2/addPliableResistanceRecord", map, successCallBack, failureCallBack);
    }

    /**
     * APP获取有氧运动列表
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSportAerobics( BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, BaseLocalDataInfo.class, "system/sport/v2/getSportAerobics", map, successCallBack, failureCallBack);
    }

    /**
     * APP获取柔韧性运动
     *
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSportPliable(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, ExerciseChildInfo.class, "system/sport/v2/getSportPliable", map, successCallBack, failureCallBack);
    }

    /**
     * APP获取抗阻运动
     *
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSportResistance(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, ExerciseChildInfo.class, "system/sport/v2/getSportResistance", map, successCallBack, failureCallBack);
    }
}
