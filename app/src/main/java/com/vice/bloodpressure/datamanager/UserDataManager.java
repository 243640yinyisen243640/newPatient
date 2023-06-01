package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.DiseaseInfo;
import com.vice.bloodpressure.model.DoctorInfo;
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
public class UserDataManager {
    /**
     * 我的医生
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSelectDoctorInfo(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DoctorInfo.class, "selectDoctorInfo", map, successCallBack, failureCallBack);
    }

    /**
     * 我的档案
     *
     * @param patientId
     * @param type
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getUserFilesInfo(String patientId, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("type", type);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "system/patient/detailApp", map, successCallBack, failureCallBack);
    }

    /**
     * 档案的家族史
     */
    public static Call<String> getUserFilesInfoForFamily(String patientId, String type, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("type", type);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, UserInfo.class, "system/patient/detailApp", map, successCallBack, failureCallBack);
    }

    /**
     * 修改档案信息
     *
     * @param key
     * @param values
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editUserFilesInfo(String archivesId, String key, String values, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put(key, values);
        return BaseNetworkUtils.putRequest(true, BaseNetworkUtils.NONE, null, "system/patient/patientApp/update", map, successCallBack, failureCallBack);
    }

    /**
     * 修改个人档案抽烟
     *
     * @param archivesId
     * @param smokes
     * @param smokesNum
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editUserFilesInfoForSmoke(String archivesId, String smokes, String smokesNum, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("smokes", smokes);
        map.put("smokesNum", smokesNum);
        return BaseNetworkUtils.putRequest(true, BaseNetworkUtils.NONE, null, "system/patient/patientApp/update", map, successCallBack, failureCallBack);
    }

    /**
     * 修改档案饮酒
     *
     * @param archivesId
     * @param wine
     * @param wineType
     * @param wineDose
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editUserFilesInfoForDrink(String archivesId, String wine, String wineType, String wineDose, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("wine", wine);
        map.put("wineType", wineType);
        map.put("wineDose", wineDose);
        return BaseNetworkUtils.putRequest(true, BaseNetworkUtils.NONE, null, "system/patient/patientApp/update", map, successCallBack, failureCallBack);
    }

    /**
     * APP-添加主要诊断疾病/其他诊断
     *
     * @param patientId
     * @param diagnosticType   疾病类型
     * @param diseaseType      诊断类型:1->主要诊断;2->其他诊断
     * @param diseaseChildType 疾病等级
     * @param diseaseRisk      高血压程度
     * @param diagnoseDate     诊断日期
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> putDiseaseImportant(String patientId, String diagnosticType, String diseaseType, String diseaseChildType, String diseaseRisk, String diagnoseDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        map.put("diseaseChildType", diseaseChildType);
        map.put("diseaseRisk", diseaseRisk);
        map.put("diagnoseDate", diagnoseDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/patient/diseaseApp/add", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param diagnosticType  :1->主要诊断;2->其他诊断
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> lookDiseaseImportant(String patientId, String diagnosticType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.NONE, DiseaseInfo.class, "system/patient/diseaseApp/isExists", map, successCallBack, failureCallBack);
    }
}
