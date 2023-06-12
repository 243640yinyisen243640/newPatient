package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HospitalInfo {
    private List<DoctorInfo> deptAppVoList;
    /**
     * 医院名称
     */
    private String hospitalName;
    /**
     * 医院头像
     */
    private String logo;
    /**
     * 医院简介
     */
    private String introduction;
    /**
     * 医院等级
     */
    private String category;
    /**
     * 医院地址
     */
    private String detailedAddress;


    public List<DoctorInfo> getDeptAppVoList() {
        return deptAppVoList;
    }

    public void setDeptAppVoList(List<DoctorInfo> deptAppVoList) {
        this.deptAppVoList = deptAppVoList;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }
}
