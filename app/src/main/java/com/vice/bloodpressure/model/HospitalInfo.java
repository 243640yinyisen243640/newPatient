package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HospitalInfo implements Serializable {
    private String office;

    private String isCheck;

    private String doctorName;
    private String doctorPost;
    private String doctorIntroduce;

    /**
     * 医院图标
     */
    private String logo;
    /**
     * 医院名称
     */
    private String name;
    /**
     * 简写
     */
    private String shortName;
    /**
     * 等级  三甲
     */
    private String category;
    /**
     * 地址
     */
    private String detailedAddress;
    /**
     * 简介
     */
    private String introduction;


    public HospitalInfo(String office, String isCheck) {
        this.office = office;
        this.isCheck = isCheck;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPost() {
        return doctorPost;
    }

    public void setDoctorPost(String doctorPost) {
        this.doctorPost = doctorPost;
    }

    public String getDoctorIntroduce() {
        return doctorIntroduce;
    }

    public void setDoctorIntroduce(String doctorIntroduce) {
        this.doctorIntroduce = doctorIntroduce;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
