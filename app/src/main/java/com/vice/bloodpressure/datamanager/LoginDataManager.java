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
     * 注册
     *
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
     * 完善信息
     *
     * @param name            姓名
     * @param idNumber        身份证号
     * @param birthDate       生日
     * @param gender          性别 1男 2女
     * @param archivesId      档案号
     * @param dm              糖尿病
     *                        0 无
     *                        1 1型
     *                        2 2型
     *                        3 妊娠
     *                        4 其他
     * @param htn             高血压
     *                        0 无
     *                        1 1级
     *                        2 2级
     *                        3 3级
     * @param chd             冠心病
     *                        0 无
     *                        1 有
     * @param copd            慢性阻塞性肺疾病
     *                        0 无
     *                        1 有
     * @param cva             脑卒中
     *                        0 无
     *                        1 有
     * @param igr             糖尿病前期
     *                        0 无
     *                        1 有
     * @param archivesId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userPerfect(String name, String idNumber, String birthDate,
                                           String gender, String dm, String htn, String chd,
                                           String copd, String cva, String igr, String archivesId, String userId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("idNumber", idNumber);
        map.put("birthDate", birthDate);
        map.put("gender", gender);
        map.put("dm", dm);
        map.put("htn", htn);
        map.put("chd", chd);
        map.put("copd", copd);
        map.put("cva", cva);
        map.put("igr", igr);
        map.put("archivesId", archivesId);
        map.put("userId", userId);
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "app/home/v2/savePatientInfo", map, successCallBack, failureCallBack);
    }

    /**
     * @param username
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> verifyCodeByTel(String username, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "auth/register", map, successCallBack, failureCallBack);
    }

    /**
     *
     * @param username
     * @param password 密码
     * @param code 验证码
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> forgetPwd(String username, String password, String code, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("code", code);
        return BaseNetworkUtils.postRequest(false, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "auth/forgotPassword", map, successCallBack, failureCallBack);
    }
}
