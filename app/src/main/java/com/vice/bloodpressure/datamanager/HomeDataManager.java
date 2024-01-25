package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.BaseLocalDataInfo;
import com.vice.bloodpressure.model.EducationInfo;
import com.vice.bloodpressure.model.ExerciseChildInfo;
import com.vice.bloodpressure.model.ExerciseInfo;
import com.vice.bloodpressure.model.HomeAllInfo;
import com.vice.bloodpressure.model.MealChildInfo;
import com.vice.bloodpressure.model.MealExclusiveInfo;
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
    public static Call<String> getSportAerobics(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
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

    /**
     * 智能教育提交答案
     *
     * @param archivesId
     * @param height
     * @param weight
     * @param mainDisease
     * @param dmType
     * @param dmComplication
     * @param dmBasics
     * @param dmTime
     * @param hbpBasics
     * @param hbpTime
     * @param chdTime
     * @param copdTime
     * @param strokeTime
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> educationAddAnswer(String archivesId, String height,
                                                  String weight, String mainDisease,
                                                  String dmType, String dmComplication,
                                                  String dmBasics, String dmTime,
                                                  String hbpBasics, String hbpTime,
                                                  String chdTime, String copdTime, String strokeTime, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("height", height);
        map.put("weight", weight);
        map.put("mainDisease", mainDisease);
        map.put("dmType", dmType);
        map.put("dmComplication", dmComplication);
        map.put("dmBasics", dmBasics);
        map.put("dmTime", dmTime);
        map.put("hbpBasics", hbpBasics);
        map.put("hbpTime", hbpTime);
        map.put("chdTime", chdTime);
        map.put("copdTime", copdTime);
        map.put("strokeTime", strokeTime);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/wellness/v2/addAnswer", map, successCallBack, failureCallBack);
    }

    /**
     * 一餐展示详情
     *
     * @param archivesId
     * @param planDate        当前时间
     * @param meals           三餐标识  breakfast 早餐，lunch 午餐 ，dinner 晚餐
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> dietDetailsToDayMeals(String archivesId, String planDate, String meals, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("planDate", planDate);
        map.put("meals", meals);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MealExclusiveInfo.class, "ai/diet/v2/dietDetailsToDayMeals", map, successCallBack, failureCallBack);
    }

    /**
     * 更换一餐饮食方案
     *
     * @param archivesId
     * @param meals           三餐标识 三餐标识  breakfast 早餐，lunch 午餐 ，dinner 晚餐
     * @param planDate
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> randomMealsPlanToDay(String archivesId, String meals, String planDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("meals", meals);
        map.put("planDate", planDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_ARRAY, MealExclusiveInfo.class, "/ai/diet/v2/randomMealsPlanToDay", map, successCallBack, failureCallBack);
    }

    /**
     * 饮食详情
     *
     * @param recId
     * @param recHeat
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> dietDetailsByRec(String recId, String recHeat, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("recId", recId);
        map.put("recHeat", recHeat);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, MealExclusiveInfo.class, "ai/diet/v2/dietDetailsByRec", map, successCallBack, failureCallBack);
    }

    /**
     * 更换一天饮食
     *
     * @param archivesId
     * @param planDate
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> randomDietPlanToDay(String archivesId, String planDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("planDate", planDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "ai/diet/v2/randomDietPlanToDay", map, successCallBack, failureCallBack);
    }

    /**
     * 获取饮食方案列表
     *
     * @param archivesId
     * @param planDate
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDietPlanList(String archivesId, String planDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("planDate", planDate);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, MealChildInfo.class, "ai/diet/v2/getDietPlanList", map, successCallBack, failureCallBack);
    }


    //教育模块

    /**
     * 智能教育系列列表
     *
     * @param typeId          类型ID
     * @param sname           系列名字过滤字段
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> teachSeriesList(String typeId, String sname, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("typeId", typeId);
        map.put("sname", sname);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, EducationInfo.class, "ai/wellness/v2/teachSeriesList", map, successCallBack, failureCallBack);
    }

    /**
     * 智能教育分类列表
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> teachTypeList(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, EducationInfo.class, "ai/wellness/v2/teachTypeList", map, successCallBack, failureCallBack);
    }

    /**
     * 智能教育详情
     *
     * @param archivesId
     * @param sid
     * @param essayId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> teachEssayInfo(String archivesId, String sid, String essayId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("sid", sid);
        map.put("essayId", essayId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, EducationInfo.class, "ai/wellness/v2/teachEssay/select", map, successCallBack, failureCallBack);
    }

    /**
     * 更改学习状态
     *
     * @param archivesId
     * @param sid
     * @param essayId
     * @param status          1 学习中 2 已完成
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> teachEssayAddOrUpdate(String archivesId, String sid, String essayId, String status, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("sid", sid);
        map.put("essayId", essayId);
        map.put("status", status);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, EducationInfo.class, "ai/wellness/v2/teachPatient/addOrUpdate", map, successCallBack, failureCallBack);
    }
}
