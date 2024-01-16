package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationAnswerInfo implements Serializable {
    /**
     * 患者id
     */
    private String patientId;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * 主要疾病   1糖尿病 2高血压 3冠心病 4慢性阻塞性肺疾病  5脑卒中 6糖尿病前期 7 都没有
     */
    private String mainDisease;
    /**
     * 糖尿病类型  1 1型糖尿病 2 2型糖尿病 3 妊娠糖尿病 4 其他
     */
    private String dmType="";
    /**
     * * 糖尿病并发症   1 无 2 糖尿病足 3 糖尿病肾病 4 糖尿病视网膜病变 5 糖尿病神经病变 6 糖尿病下肢血管病变
     */
    private String dmComplication="";
    /**
     * * 糖尿病基础知识 1 是 2 否
     */
    private String dmBasics="";
    /**
     * 糖尿病患病时间 1 小于1年 2 1-5年 3 大于5年
     */
    private String dmTime="";
    /**
     * 高血压基础知识 1 是 2 否
     */
    private String hbpBasics="";
    /**
     * 高血压患病时间 1 小于1年 2 1-5年 3 大于5年
     */
    private String hbpTime="";
    /**
     * 冠心病患病时间 1 小于1年 2 1-5年 3 大于5年
     */
    private String chdTime="";
    /**
     * 慢性阻塞性肺疾病患病时间 1 小于1年 2 1-5年 3 大于5年
     */
    private String copdTime="";
    /**
     * 脑卒中患病时间 1 小于1年 2 1-5年 3 大于5年
     */
    private String strokeTime="";

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMainDisease() {
        return mainDisease;
    }

    public void setMainDisease(String mainDisease) {
        this.mainDisease = mainDisease;
    }

    public String getDmType() {
        return dmType;
    }

    public void setDmType(String dmType) {
        this.dmType = dmType;
    }

    public String getDmComplication() {
        return dmComplication;
    }

    public void setDmComplication(String dmComplication) {
        this.dmComplication = dmComplication;
    }

    public String getDmBasics() {
        return dmBasics;
    }

    public void setDmBasics(String dmBasics) {
        this.dmBasics = dmBasics;
    }

    public String getDmTime() {
        return dmTime;
    }

    public void setDmTime(String dmTime) {
        this.dmTime = dmTime;
    }

    public String getHbpBasics() {
        return hbpBasics;
    }

    public void setHbpBasics(String hbpBasics) {
        this.hbpBasics = hbpBasics;
    }

    public String getHbpTime() {
        return hbpTime;
    }

    public void setHbpTime(String hbpTime) {
        this.hbpTime = hbpTime;
    }

    public String getChdTime() {
        return chdTime;
    }

    public void setChdTime(String chdTime) {
        this.chdTime = chdTime;
    }

    public String getCopdTime() {
        return copdTime;
    }

    public void setCopdTime(String copdTime) {
        this.copdTime = copdTime;
    }

    public String getStrokeTime() {
        return strokeTime;
    }

    public void setStrokeTime(String strokeTime) {
        this.strokeTime = strokeTime;
    }
}
