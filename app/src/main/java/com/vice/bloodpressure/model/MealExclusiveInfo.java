package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealExclusiveInfo {
    /**
     * 食谱id
     */
    private String recId;
    /**
     * 食谱名称
     */
    private String recName;
    /**
     * 食谱热量
     */
    private String recHeat;
    /**
     * 食物类型
     */
    private String recType;
    /**
     * 食物二级类型
     */
    private String recChildType;
    /**
     * 食材名称
     */
    private String name;
    /**
     * 食材克数
     */
    private String ingK;

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName;
    }

    public String getRecHeat() {
        return recHeat;
    }

    public void setRecHeat(String recHeat) {
        this.recHeat = recHeat;
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType;
    }

    public String getRecChildType() {
        return recChildType;
    }

    public void setRecChildType(String recChildType) {
        this.recChildType = recChildType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngK() {
        return ingK;
    }

    public void setIngK(String ingK) {
        this.ingK = ingK;
    }
}
