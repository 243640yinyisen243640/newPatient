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

    /**
     * 菜品名称
     */
    private String dishName;
    /**
     * 菜品图片
     */
    private String pic;
    /**
     * 菜品id
     */
    private String id;
    /**
     * 是否收藏  true 收藏 false 没收藏
     */
    private boolean isCollect;
    /**
     * 视频
     */
    private String vid;


    private List<MealIngMapInfo> ingMap;
    /**
     * 食材清单-材料
     */
    private List<MealIngMapInfo> ingData;
    /**
     * 制作饮食原料占比
     */
    private List<MealIngMapInfo> ingRatio;
    /**
     * 视频详情 材料列表
     */
    private List<MealIngMapInfo> materialList;
    /**
     * 视频详情 调料列表
     */
    private List<MealIngMapInfo> seasoningList;
    /**
     * 饮食封面图
     */
    private String coverUrl;
    private String videoUrl;

    private List<MealIngMapInfo> calorieRatio;

    public List<MealIngMapInfo> getCalorieRatio() {
        return calorieRatio;
    }

    public void setCalorieRatio(List<MealIngMapInfo> calorieRatio) {
        this.calorieRatio = calorieRatio;
    }

    public List<MealIngMapInfo> getIngRatio() {
        return ingRatio;
    }

    public void setIngRatio(List<MealIngMapInfo> ingRatio) {
        this.ingRatio = ingRatio;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<MealIngMapInfo> getIngData() {
        return ingData;
    }

    public void setIngData(List<MealIngMapInfo> ingData) {
        this.ingData = ingData;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public List<MealIngMapInfo> getSeasoningList() {
        return seasoningList;
    }

    public void setSeasoningList(List<MealIngMapInfo> seasoningList) {
        this.seasoningList = seasoningList;
    }

    public List<MealIngMapInfo> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MealIngMapInfo> materialList) {
        this.materialList = materialList;
    }



    @Override
    public String getImplMealPic() {
        return pic;
    }

    @Override
    public String getImplDashName() {
        return dishName;
    }
}
