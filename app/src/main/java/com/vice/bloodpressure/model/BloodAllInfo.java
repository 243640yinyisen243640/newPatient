package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class BloodAllInfo {
    /**
     * 最小值
     */
    private String min;
    /**
     * 平均值
     */
    private String avg;
    /**
     * 最大值
     */
    private String max;
    /**
     * 总数
     */
    private String bgSize;
    /**
     * 空腹次数
     */
    private String limosisCount;
    /**
     * 非空腹次数
     */
    private String unLimosisCount;


    private List<BloodChildInfo> records;

    public List<BloodChildInfo> getRecords() {
        return records;
    }

    public void setRecords(List<BloodChildInfo> records) {
        this.records = records;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getBgSize() {
        return bgSize;
    }

    public void setBgSize(String bgSize) {
        this.bgSize = bgSize;
    }

    public String getLimosisCount() {
        return limosisCount;
    }

    public void setLimosisCount(String limosisCount) {
        this.limosisCount = limosisCount;
    }

    public String getUnLimosisCount() {
        return unLimosisCount;
    }

    public void setUnLimosisCount(String unLimosisCount) {
        this.unLimosisCount = unLimosisCount;
    }
}
