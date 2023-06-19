package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.DiseaseInfo;
import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.model.EquipmetInfo;
import com.vice.bloodpressure.model.MealExclusiveInfo;
import com.vice.bloodpressure.model.MessageInfo;
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
     *
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
     * @param diseaseType      疾病类型
     * @param diagnosticType   疾病类型 诊断类型:1->主要诊断;2->其他诊断
     * @param diseaseChildType 疾病等级
     * @param diseaseRisk      高血压程度
     * @param diagnoseDate     诊断日期
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> putDiseaseImportant(String patientId, String diseaseType, String diagnosticType, String diseaseChildType, String diseaseRisk, String diagnoseDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
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
     * APP-修改患者疾病详情
     *
     * @param patientId
     * @param diseaseType      诊断类型:1->主要诊断;2->其他诊断
     * @param diagnosticType   疾病类型
     * @param diseaseChildType 疾病等级
     * @param diseaseRisk      高血压程度
     * @param diagnoseDate     诊断日期
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editDiseaseImportant(String patientId, String diseaseType, String diagnosticType, String diseaseChildType, String diseaseRisk, String diagnoseDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        map.put("diseaseChildType", diseaseChildType);
        map.put("diseaseRisk", diseaseRisk);
        map.put("diagnoseDate", diagnoseDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/patient/app/updateDisease", map, successCallBack, failureCallBack);
    }

    /**
     * APP-合并症修改
     *
     * @param patientId
     * @param diagnosticType   诊断类型1主要 2其他
     * @param diseaseType      疾病类型
     * @param complicationType
     * @param level
     * @param complicationDate
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editDiseasePlus(String patientId, String diagnosticType, String diseaseType, String complicationType, String level, String complicationDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        map.put("complicationType", complicationType);
        map.put("level", level);
        map.put("complicationDate", complicationDate);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/patient/app/updateComplication", map, successCallBack, failureCallBack);
    }

    /**
     * 添加并发症
     *
     * @param patientId
     * @param complicationType 并发症类型
     * @param complicationName
     * @param level            患病程度
     * @param complicationDate 并发症确诊日期
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> putDiseasePlus(String patientId, String complicationType, String complicationName, String level, String complicationDate, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("complicationType", complicationType);
        map.put("complicationName", complicationName);
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
     * @param patientId
     * @param diseaseType     疾病类型  1, 糖尿病2, 高血压3, 糖尿病前期4,冠心病5, 脑卒中6, 慢阻肺
     * @param diagnosticType  诊断类型 1主要诊断 2其他诊断
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDiseaseImportantDetails(String patientId, String diseaseType, String diagnosticType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diseaseType", diseaseType);
        map.put("diagnosticType", diagnosticType);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DiseaseInfo.class, "system/patient/app/diseaseDetail", map, successCallBack, failureCallBack);
    }

    /**
     * @param patientId
     * @param diagnosticType   诊断类型  1主要诊断 2其他诊断
     * @param diseaseType
     * @param complicationType
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDiseasePlusDetails(String patientId, String diagnosticType, String diseaseType, String complicationType, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("diagnosticType", diagnosticType);
        map.put("diseaseType", diseaseType);
        map.put("complicationType", complicationType);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DiseaseInfo.class, "system/patient/app/complicationDetail", map, successCallBack, failureCallBack);
    }

    /**
     * APP-查询合并症是否存在
     *
     * @param patientId
     * @return
     */
    public static Call<String> lookDiseasePlus(String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
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

    /**
     * @param patientId
     * @param collectType     收藏类型 1文章 2视频 3商品
     * @param pageNum
     * @param pageSize
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getCollectMealList(String patientId, String collectType, String pageNum, String pageSize, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        map.put("collectType", collectType);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MealExclusiveInfo.class, "ai/patient/patientCollect/list", map, successCallBack, failureCallBack);
    }

    /**
     * 注销
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> offAccount(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "auth/logoff", map, successCallBack, failureCallBack);
    }

    /**
     * 修改登录密码
     *
     * @param userId
     * @param password
     * @param newPassword
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> editLoginPwd(String userId, String password, String newPassword, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "system/user/updatePassword", map, successCallBack, failureCallBack);
    }

    /**
     * 消息列表
     *
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getMessageList(String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, MessageInfo.class, "monitor/api/v2/message/list", map, successCallBack, failureCallBack);
    }

    /**
     * 全部已读
     *
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> readMessageList(String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("patientId", patientId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "monitor/api/v2/message/readAll", map, successCallBack, failureCallBack);
    }

    /**
     * 已读单个消息
     * @param messageId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> readOneMessage(String messageId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("messageId", messageId);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.NONE, null, "mointor/api/v2/message/read", map, successCallBack, failureCallBack);
    }

    /**
     * 根据设备号获取设备详情
     * @param deviceCode
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getScanInfo(String deviceCode, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceCode", deviceCode);
        return BaseNetworkUtils.postRequest(true, BaseNetworkUtils.JSON_OBJECT, EquipmetInfo.class, "system/device/v2/app/selectDeviceByCode", map, successCallBack, failureCallBack);
    }
}
