package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealSecondInfo {
    /**
     * 早餐数据
     */
    private List<MealExclusiveInfo> breakfast;
    /**
     * 午餐数据
     */
    private List<MealExclusiveInfo> lunch;
    /**
     * 晚餐数据
     */
    private List<MealExclusiveInfo> dinner;
    /**
     * 配料信息
     */
    private MealFormulasInfo formulas;
    /**
     * 时间
     */
    private String planDate;
    /**
     * 本地数据 是否选中
     */
    private boolean isCheck;


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

    public MealFormulasInfo getFormulas() {
        return formulas;
    }

    public void setFormulas(MealFormulasInfo formulas) {
        this.formulas = formulas;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
