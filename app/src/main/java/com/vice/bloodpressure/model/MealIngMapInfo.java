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
