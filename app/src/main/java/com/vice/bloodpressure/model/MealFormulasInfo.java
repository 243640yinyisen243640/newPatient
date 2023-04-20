package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealFormulasInfo {
    /**
     * 三餐
     */
    private String threeMeals;
    /**
     * 主料
     */
    private String major;
    /**
     * 辅料
     */
    private String minor;

    public String getThreeMeals() {
        return threeMeals;
    }

    public void setThreeMeals(String threeMeals) {
        this.threeMeals = threeMeals;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }
}
