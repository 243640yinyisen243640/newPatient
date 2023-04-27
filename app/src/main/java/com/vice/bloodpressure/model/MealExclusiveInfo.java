package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealExclusiveInfo implements Serializable, IClassInfo {
    private String img;
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
     * 调料
     */
    private String seasoning;
    /**
     * 做法
     */
    private String practice;

    private List<MealIngMapInfo> ingMap;

    public MealExclusiveInfo(String img, String recName) {
        this.img = img;
        this.recName = recName;
    }

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

    public String getSeasoning() {
        return seasoning;
    }

    public void setSeasoning(String seasoning) {
        this.seasoning = seasoning;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public List<MealIngMapInfo> getIngMap() {
        return ingMap;
    }

    public void setIngMap(List<MealIngMapInfo> ingMap) {
        this.ingMap = ingMap;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String getImplClassID() {
        return null;
    }

    @Override
    public String getImplClassName() {
        return recName;
    }

    @Override
    public String getImplClassImg() {
        return img;
    }
}
