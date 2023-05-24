package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HealthyDataChildInfo {
    /**
     * 记录方式:1->自动记录;2->手动记录
     */

    private String recordType;
    /**
     * 收缩压
     */

    private String sbp;
    /**
     * 舒张压
     */

    private String dbp;
    /**
     * 心率
     */

    private String hr;
    /**
     * 添加时间
     */

    private String addTime;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * bmi
     */
    private String bmi;
    /**
     * 状态 1偏低 2正常 3偏高
     */
    private String status;
    /**
     * 血氧
     */
    private String spo;
    /**
     * 体温
     */
    private String temp;
    /**
     * 值
     */
    private String bgValue;

    /**
     * 运动类型
     */
    private String sportName;
    /**
     * 运动时间
     */
    private String sportTime;
    /**
     * 药品名称
     */

    private String drugName;
    /**
     * 药品规格单位
     */

    private String drugSpecUnit;
    /**
     * 药品规格
     */

    private String drugSpec;
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
     * 结束时间
     */

    private String endTime;
    /**
     * 结束时间
     */

    private String finishTime;
    /**
     * 药品ID
     */
    private String pkId;

    /**
     * 提醒时间
     */
    private String wranTime;
    /**
     * 药品数量
     */
    private String drugNumber;
    /**
     * 药品用法(1饭前服,2饭后服,3舌下含服,4口服,5水煎服,6露化吸乳,7喉咙,8静滴,9肌注,10嚼服,11冲服,12外用,13外敷,14外洗,15皮下注射)
     */
    private String drugMode;
    /**
     * 用药剂量单位
     */
    private String drugDoseUnit;
    /**
     * 食物名称
     */
    private String foodName;
    /**
     * 卡路里
     */
    private String foodBigCards;
    /**
     * 重量
     */
    private String foodWeight;
    /**
     * 检验检查项目名称
     */
    private String projectName;
    /**
     * 检验检查项目封面图
     */
    private String fileItem;

    private List<String> fileUrls;

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
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

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpo() {
        return spo;
    }

    public void setSpo(String spo) {
        this.spo = spo;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getBgValue() {
        return bgValue;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }


    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugSpecUnit() {
        return drugSpecUnit;
    }

    public void setDrugSpecUnit(String drugSpecUnit) {
        this.drugSpecUnit = drugSpecUnit;
    }

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getWranTime() {
        return wranTime;
    }

    public void setWranTime(String wranTime) {
        this.wranTime = wranTime;
    }

    public String getDrugNumber() {
        return drugNumber;
    }

    public void setDrugNumber(String drugNumber) {
        this.drugNumber = drugNumber;
    }

    public String getDrugMode() {
        return drugMode;
    }

    public void setDrugMode(String drugMode) {
        this.drugMode = drugMode;
    }

    public String getDrugDoseUnit() {
        return drugDoseUnit;
    }

    public void setDrugDoseUnit(String drugDoseUnit) {
        this.drugDoseUnit = drugDoseUnit;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodBigCards() {
        return foodBigCards;
    }

    public void setFoodBigCards(String foodBigCards) {
        this.foodBigCards = foodBigCards;
    }

    public String getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(String foodWeight) {
        this.foodWeight = foodWeight;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFileItem() {
        return fileItem;
    }

    public void setFileItem(String fileItem) {
        this.fileItem = fileItem;
    }


    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }
}
