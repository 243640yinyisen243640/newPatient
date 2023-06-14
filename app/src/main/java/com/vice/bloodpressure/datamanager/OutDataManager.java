package com.vice.bloodpressure.datamanager;

import com.vice.bloodpressure.model.DoctorInfo;
import com.vice.bloodpressure.model.HospitalInfo;
import com.vice.bloodpressure.model.ProvinceInfo;
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
public class OutDataManager {
    /**
     * 获取省份
     *
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getProvinceList(BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, ProvinceInfo.class, "system/area/getAreaProvinceApp", map, successCallBack, failureCallBack);
    }

    /**
     * 获取城市
     *
     * @param provinceId      城市id
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getCityList(String provinceId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("provinceId", provinceId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, ProvinceInfo.class, "system/area/getAreaCityApp", map, successCallBack, failureCallBack);
    }

    /**
     * 获取医院列表
     *
     * @param provinceId
     * @param cityId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> gethospitalList(String provinceId, String cityId, String hospitalName, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("provinceId", provinceId);
        map.put("cityId", cityId);
        map.put("hospitalName", hospitalName);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, DoctorInfo.class, "system/hosp/v2/appHospitalList", map, successCallBack, failureCallBack);
    }

    /**
     * APP-查询医院下所有科室
     *
     * @param hospitalId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDeptList(String hospitalId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("hospitalId", hospitalId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HospitalInfo.class, "system/dept/appListDept", map, successCallBack, failureCallBack);
    }

    /**
     * @param name            医生名称
     * @param roleId          角色ID   1主任2副主任3医生4护士
     * @param deptId          科室ID
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDeptDoctorList(String name, String roleId, String deptId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("roleId", roleId);
        map.put("deptId", deptId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_ARRAY, DoctorInfo.class, "system/doctor/v2/appDoctorList", map, successCallBack, failureCallBack);
    }

    /**
     * 院外管理获取医生详情
     *
     * @param doctorId
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getDeptDoctorInfo(String doctorId, String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("doctorId", doctorId);
        map.put("patientId", patientId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, DoctorInfo.class, "system/doctor/v2/appDoctor/detail", map, successCallBack, failureCallBack);
    }

    /**
     * 解绑医生
     *
     * @param doctorId
     * @param patientId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> unBindDoctor(String doctorId, String patientId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("doctorId", doctorId);
        map.put("patientId", patientId);
        return BaseNetworkUtils.putRequest(true, BaseNetworkUtils.NONE, null, "system/patient/app/unbindExternal", map, successCallBack, failureCallBack);
    }

    /**
     * 绑定医生
     *
     * @param archivesId
     * @param doctorId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> bindDoctor(String archivesId, String doctorId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("archivesId", archivesId);
        map.put("doctorId", doctorId);
        return BaseNetworkUtils.putRequest(true, BaseNetworkUtils.NONE, null, "system/patient/patientApp/update", map, successCallBack, failureCallBack);
    }

    /**
     * 医院详情
     *
     * @param hospitalId
     * @param successCallBack
     * @param failureCallBack
     * @return
     */
    public static Call<String> getHospitalInfo(String hospitalId, BiConsumer<Call<String>, BaseResponse> successCallBack, BiConsumer<Call<String>, Throwable> failureCallBack) {
        Map<String, String> map = new HashMap<>();
        map.put("hospitalId", hospitalId);
        return BaseNetworkUtils.getRequest(true, BaseNetworkUtils.JSON_OBJECT, HospitalInfo.class, "system/hosp/v2/app/select", map, successCallBack, failureCallBack);
    }
}
