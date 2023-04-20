package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MealInfo implements Serializable {
    private String img;
    private String title;
    private String num;

    private String color;

    private String id;

    private MealChildInfo dietNutritionVo;
    private MealChildInfo threeMealVo;
    private List<MealSecondInfo> exclusiveDietPlanVos;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MealInfo() {
    }

    public MealInfo(String img, String title) {
        this.img = img;
        this.title = title;
    }

    public MealInfo(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MealChildInfo getDietNutritionVo() {
        return dietNutritionVo;
    }

    public void setDietNutritionVo(MealChildInfo dietNutritionVo) {
        this.dietNutritionVo = dietNutritionVo;
    }

    public MealChildInfo getThreeMealVo() {
        return threeMealVo;
    }

    public void setThreeMealVo(MealChildInfo threeMealVo) {
        this.threeMealVo = threeMealVo;
    }

    public List<MealSecondInfo> getExclusiveDietPlanVos() {
        return exclusiveDietPlanVos;
    }

    public void setExclusiveDietPlanVos(List<MealSecondInfo> exclusiveDietPlanVos) {
        this.exclusiveDietPlanVos = exclusiveDietPlanVos;
    }
}
