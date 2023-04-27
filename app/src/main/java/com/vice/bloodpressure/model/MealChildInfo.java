package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealChildInfo implements Serializable {
    /**
     * 主食（两）
     */
    private String cereals;
    /**
     * 蔬菜（两）
     */
    private String vegetable;
    /**
     * 肉蛋豆（两）
     */
    private String meat;
    /**
     * 浆乳类（ml）
     */
    private String drinks;
    /**
     * 盐（克）
     */
    private String salt;
    /**
     * 油（汤匙）
     */
    private String oil;
    /**
     * 总热量（千卡） 这个是饮食智能饮食方案中的
     */
    private String sumCalorie;
    /**
     * 碳水化合物高范围
     */
    private String highCarbs;
    /**
     * 碳水化合物热量高范围
     */
    private String highCarbsCalorie;
    /**
     * 碳水化合物低范围
     */
    private String lowCarbs;
    /**
     * 碳水化合物热量低范围
     */
    private String lowCarbsCalorie;
    /**
     * 碳水化合物热量低范围
     */
    private String highPro;
    /**
     * 蛋白质高范围
     */
    private String highProCalorie;
    /**
     * 蛋白质低范围
     */
    private String lowPro;
    /**
     * 蛋白质热量高范围
     */
    private String lowProCalorie;
    /**
     * 脂肪
     */
    private String fat;
    /**
     * 脂肪热量
     */
    private String fatCalorie;
    /**
     * 早餐
     */
    private List<MealExclusiveInfo> breakfast;
    /**
     * 午餐
     */
    private List<MealExclusiveInfo> lunch;
    /**
     * 晚餐
     */
    private List<MealExclusiveInfo> dinner;
    /**
     * *配料信息
     */
    private MealExclusiveInfo formulas;
    /**
     * 方案日期
     */
    private String planDate;
    /**
     * 需摄入总热量 这个是在首页饮食方案中用到的
     */
    private String sumCalories;
    /**
     * 三餐标识
     */
    private String meals;
    /**
     * 方案id
     */
    private String dietPlanId;
    /**
     * 饮食计划
     */
    private List<MealExclusiveInfo> dietPlan;

    public String getSumCalories() {
        return sumCalories;
    }

    public void setSumCalories(String sumCalories) {
        this.sumCalories = sumCalories;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getDietPlanId() {
        return dietPlanId;
    }

    public void setDietPlanId(String dietPlanId) {
        this.dietPlanId = dietPlanId;
    }

    public List<MealExclusiveInfo> getDietPlan() {
        return dietPlan;
    }

    public void setDietPlan(List<MealExclusiveInfo> dietPlan) {
        this.dietPlan = dietPlan;
    }

    public String getCereals() {
        return cereals;
    }

    public void setCereals(String cereals) {
        this.cereals = cereals;
    }

    public String getVegetable() {
        return vegetable;
    }

    public void setVegetable(String vegetable) {
        this.vegetable = vegetable;
    }

    public String getMeat() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat = meat;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getSumCalorie() {
        return sumCalorie;
    }

    public void setSumCalorie(String sumCalorie) {
        this.sumCalorie = sumCalorie;
    }

    public String getHighCarbs() {
        return highCarbs;
    }

    public void setHighCarbs(String highCarbs) {
        this.highCarbs = highCarbs;
    }

    public String getHighCarbsCalorie() {
        return highCarbsCalorie;
    }

    public void setHighCarbsCalorie(String highCarbsCalorie) {
        this.highCarbsCalorie = highCarbsCalorie;
    }

    public String getLowCarbs() {
        return lowCarbs;
    }

    public void setLowCarbs(String lowCarbs) {
        this.lowCarbs = lowCarbs;
    }

    public String getLowCarbsCalorie() {
        return lowCarbsCalorie;
    }

    public void setLowCarbsCalorie(String lowCarbsCalorie) {
        this.lowCarbsCalorie = lowCarbsCalorie;
    }

    public String getHighPro() {
        return highPro;
    }

    public void setHighPro(String highPro) {
        this.highPro = highPro;
    }

    public String getHighProCalorie() {
        return highProCalorie;
    }

    public void setHighProCalorie(String highProCalorie) {
        this.highProCalorie = highProCalorie;
    }

    public String getLowPro() {
        return lowPro;
    }

    public void setLowPro(String lowPro) {
        this.lowPro = lowPro;
    }

    public String getLowProCalorie() {
        return lowProCalorie;
    }

    public void setLowProCalorie(String lowProCalorie) {
        this.lowProCalorie = lowProCalorie;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getFatCalorie() {
        return fatCalorie;
    }

    public void setFatCalorie(String fatCalorie) {
        this.fatCalorie = fatCalorie;
    }

    public List<MealExclusiveInfo> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<MealExclusiveInfo> breakfast) {
        this.breakfast = breakfast;
    }

    public List<MealExclusiveInfo> getLunch() {
        return lunch;
    }

    public void setLunch(List<MealExclusiveInfo> lunch) {
        this.lunch = lunch;
    }

    public List<MealExclusiveInfo> getDinner() {
        return dinner;
    }

    public void setDinner(List<MealExclusiveInfo> dinner) {
        this.dinner = dinner;
    }

    public MealExclusiveInfo getFormulas() {
        return formulas;
    }

    public void setFormulas(MealExclusiveInfo formulas) {
        this.formulas = formulas;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }
}
