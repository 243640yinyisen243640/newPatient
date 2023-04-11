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
public class LoginDataManager {
    /**
     * @param username        手机号
     * @param password        密码
     * @param code            验证码
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userRegister(String username, String password, String code, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("code", code);
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "auth/register", map, successCallBack, failureCallBack);
    }

    /**
     * @param name            姓名
     * @param idNumber        身份证号
     * @param birthDate       生日
     * @param gender          性别 1男 2女
     * @param diseases        疾病类型
     *                        no -> 无
     *                        dm -> 糖尿病
     *                        htn -> 高血压
     *                        chd -> 冠心病
     *                        copd -> 慢性阻塞性肺疾病
     *                        cva -> 脑卒中
     *                        igr -> 糖尿病前期
     * @param dmType          糖尿病
     *                        0 无
     *                        1 1型
     *                        2 2型
     *                        3 妊娠
     *                        4 其他
     *                        5 未知
     * @param htnGrade        高血压
     *                        0 无
     *                        1 1级
     *                        2 2级
     *                        3 3级
     *                        4 未知
     * @param archivesId      档案号
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userPerfect(String name, String idNumber, String birthDate,
                                           String gender, String diseases, String dmType,
                                           String htnGrade, String archivesId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("idNumber", idNumber);
        map.put("birthDate", birthDate);
        map.put("gender", gender);
        map.put("diseases", diseases);
        map.put("dmType", dmType);
        map.put("htnGrade", htnGrade);
//        map.put("archivesId", archivesId);
        map.put("archivesId", "155");
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "app/home/v2/savePatientInfo", map, successCallBack, failureCallBack);
    }
}
