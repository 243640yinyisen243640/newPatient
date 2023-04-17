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
     * @param archivesId      档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDietPlan(String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        return BaseNetworkUtils.getRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "ai/diet/v2/getDietPlanByArchivesId", map, successCallBack, failureCallBack);
    }

    /**
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
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "ai/diet/v2/recommendDietPlan", map, successCallBack, failureCallBack);
    }

    /**
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
     * @param whetherEmptySport 是否空腹运动 Y N
     * @param exerciseTime 运动时间
     * @param exerciseFrequency 运动频次
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> recommendSportPlan(String height, String weight, String diseases,
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
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "ai/sport/v2/recommendSportPlan", map, successCallBack, failureCallBack);
    }
}
