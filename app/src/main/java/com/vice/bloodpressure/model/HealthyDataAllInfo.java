package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HealthyDataAllInfo {
    /**
     * 七日血糖最高值
     */
    private String hbMax;
    /**
     * 七日血糖最低值
     */
    private String hbMin;
    /**
     * 最大收缩压
     */

    private String maxSbpValue;
    /**
     * 最小收缩压
     */

    private String mixSbpValue;
    /**
     * 最大舒张压
     */

    private String maxDbpValue;
    /**
     * 最小收缩压
     */

    private String mixDbpValue;
    /**
     * bmi 最大值
     */

    private String maxValue;
    /**
     * bmi 平均值
     */

    private String minValue;
    /**
     * bmi 最小值
     */

    private String avgValue;
    /**
     * 药品名称
     */

    private String drugName;
    /**
     * 用药次数
     */

    private String drugTimes;
    /**
     * 用药剂量
     */

    private String drugDose;
    /**
     * 用药单位
     */

    private String drugUnit;
    /**
     * 添加时间
     */

    private String addTime;
    /**
     * 结束时间
     */

    private String endTime;
    /**
     * 记录方式:1->自动记录;2->手动记录
     */

    private String recordType;


    private List<HealthyDataChildInfo> value;

    public String getHbMax() {
        return hbMax;
    }

    public void setHbMax(String hbMax) {
        this.hbMax = hbMax;
    }

    public String getHbMin() {
        return hbMin;
    }

    public void setHbMin(String hbMin) {
        this.hbMin = hbMin;
    }

    public List<HealthyDataChildInfo> getValue() {
        return value;
    }

    public void setValue(List<HealthyDataChildInfo> value) {
        this.value = value;
    }


    public String getMaxSbpValue() {
        return maxSbpValue;
    }

    public void setMaxSbpValue(String maxSbpValue) {
        this.maxSbpValue = maxSbpValue;
    }

    public String getMixSbpValue() {
        return mixSbpValue;
    }

    public void setMixSbpValue(String mixSbpValue) {
        this.mixSbpValue = mixSbpValue;
    }

    public String getMaxDbpValue() {
        return maxDbpValue;
    }

    public void setMaxDbpValue(String maxDbpValue) {
        this.maxDbpValue = maxDbpValue;
    }

    public String getMixDbpValue() {
        return mixDbpValue;
    }

    public void setMixDbpValue(String mixDbpValue) {
        this.mixDbpValue = mixDbpValue;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public String getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(String avgValue) {
        this.avgValue = avgValue;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugTimes() {
        return drugTimes;
    }

    public void setDrugTimes(String drugTimes) {
        this.drugTimes = drugTimes;
    }

    public String getDrugDose() {
        return drugDose;
    }

    public void setDrugDose(String drugDose) {
        this.drugDose = drugDose;
    }

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
