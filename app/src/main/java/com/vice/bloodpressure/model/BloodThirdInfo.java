package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class BloodThirdInfo {
    /**
     * 血糖状态 1偏低 2正常 3偏高
     */
    private String bgStatus;
    /**
     * 血糖数值
     */
    private String bgValue;

    /**
     * 类型次数  大于1的时候就是有多条数据
     */
    private String bgCount;
    /**
     * 添加时间
     */
    private String addTime;

    /**
     * 记录方式:1->自动记录;2->手动记录
     */
    private String recordType;

    public String getBgStatus() {
        return bgStatus;
    }

    public void setBgStatus(String bgStatus) {
        this.bgStatus = bgStatus;
    }

    public String getBgValue() {
        return bgValue;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public String getBgCount() {
        return bgCount;
    }

    public void setBgCount(String bgCount) {
        this.bgCount = bgCount;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}
