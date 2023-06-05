package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.DiseaseInfo;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.model.EquipmetInfo;
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
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getSelectDoctorInfo(String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DoctorInfo.class, "system/doctor/v2/myDoctor/detail", map, successCallBack, failureCallBack);
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
     * 添加家族史
     * @param archivesId
     * @param familyType
     * @param familyValue
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editUserFilesInfoForFamily(String archivesId, String familyType, String familyValue, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("familyType", familyType);
        map.put("familyValue", familyValue);
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
     * 添加并发症
     *
     * @param patientId
     * @param complicationType 并发症类型
     * @param diseaseChildType
     * @param level            患病程度
     * @param complicationDate 并发症确诊日期
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> putDiseasePlus(String patientId, String complicationType, String diseaseChildType, String level, String complicationDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("complicationType", complicationType);
        map.put("diseaseChildType", diseaseChildType);
        map.put("level", level);
        map.put("complicationDate", complicationDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/patient/complication/add", map, successCallBack, failureCallBack);
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
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DiseaseInfo.class, "system/patient/diseaseApp/isExists", map, successCallBack, failureCallBack);
    }

    /**
     * APP-查询合并症是否存在
     *
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> lookDiseasePlus(String patientId, String diagnosticType, String diseaseType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DiseaseInfo.class, "system/patient/complication/isExists", map, successCallBack, failureCallBack);
    }

    /**
     * 个人中心
     *
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getUserInfo(String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, UserInfo.class, "system/patient/app/patientCenter", map, successCallBack, failureCallBack);
    }

    /**
     * 我的设备列表
     *
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getUserEquipmetList(String patientId, String brandCode, String deviceCategory, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("brandCode", brandCode);
        map.put("deviceCategory", deviceCategory);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, EquipmetInfo.class, "system/device/bindApp/list", map, successCallBack, failureCallBack);
    }

    /**
     * 解绑设备
     *
     * @param pkId
     * @param deviceId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userUnbindDevice(String pkId, String deviceId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("pkId", pkId);
        map.put("deviceId", deviceId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/device/v2/unbindDeviceApp", map, successCallBack, failureCallBack);
    }

    /**
     * 绑定设备
     *
     * @param brandId         设备id
     * @param deviceCategory  设备分类:1->血糖仪;2->血压计;
     * @param deviceCode      设备号
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> userBindDevice(String brandId, String deviceCategory, String deviceCode, String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("brandId", brandId);
        map.put("deviceCategory", deviceCategory);
        map.put("deviceCode", deviceCode);
        map.put("patientId", patientId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/device/v2/bindDeviceApp", map, successCallBack, failureCallBack);
    }

    /**
     * 设备名称列表
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getEquipmentNameList(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, EquipmetInfo.class, "system/deviceBrand/list", map, successCallBack, failureCallBack);
    }

    /**
     * 添加家族史
     *
     * @param patientId
     * @param diagnosticType
     * @param diseaseType
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> putFamilyIll(String patientId, String diagnosticType, String diseaseType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/patient/diseaseApp/add", map, successCallBack, failureCallBack);
    }

}
