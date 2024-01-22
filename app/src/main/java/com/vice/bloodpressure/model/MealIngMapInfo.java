package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealIngMapInfo implements Serializable {
    /**
     * 食材名称
     */
    private String name;
    /**
     * 食材克数
     */
    private String ingK;
    /**
     * 食材克数
     */
    private String weight;
    /**
     * 原料占比
     */
    private String ingRatio;

    /**
     * 卡路里占比
     */
    private String calorieRatio;
    /**
     * 调料名称
     */
    private String seasonings;

    public String getSeasonings() {
        return seasonings;
    }

    public void setSeasonings(String seasonings) {
        this.seasonings = seasonings;
    }

    public String getCalorieRatio() {
        return calorieRatio;
    }

    public void setCalorieRatio(String calorieRatio) {
        this.calorieRatio = calorieRatio;
    }

    public String getIngRatio() {
        return ingRatio;
    }

    public void setIngRatio(String ingRatio) {
        this.ingRatio = ingRatio;
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


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
