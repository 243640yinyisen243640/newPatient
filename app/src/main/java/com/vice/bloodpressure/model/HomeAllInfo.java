package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HomeAllInfo {
    private String archivesId;
    /**
     * 健康提示
     */
    private List<MessageInfo> healthyTag;
    /**
     * 个人信息
     */
    private UserInfo basicInfo;
    /**
     * 血糖模块
     */
    private UserInfo monitorBgs;
    /**
     * 血压模块
     */
    private UserInfo bpModule;
    /**
     * bmi模块
     */
    private UserInfo BmiModule;
    /**
     * 饮食模块
     */
    private MealChildInfo dietModule;
    /**
     * 运动模块
     */
    private ExerciseInfo sportModule;

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public List<MessageInfo> getHealthyTag() {
        return healthyTag;
    }

    public void setHealthyTag(List<MessageInfo> healthyTag) {
        this.healthyTag = healthyTag;
    }

    public UserInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(UserInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public UserInfo getMonitorBgs() {
        return monitorBgs;
    }

    public void setMonitorBgs(UserInfo monitorBgs) {
        this.monitorBgs = monitorBgs;
    }

    public UserInfo getBpModule() {
        return bpModule;
    }

    public void setBpModule(UserInfo bpModule) {
        this.bpModule = bpModule;
    }

    public UserInfo getBmiModule() {
        return BmiModule;
    }

    public void setBmiModule(UserInfo bmiModule) {
        BmiModule = bmiModule;
    }

    public MealChildInfo getDietModule() {
        return dietModule;
    }

    public void setDietModule(MealChildInfo dietModule) {
        this.dietModule = dietModule;
    }

    public ExerciseInfo getSportModule() {
        return sportModule;
    }

    public void setSportModule(ExerciseInfo sportModule) {
        this.sportModule = sportModule;
    }
}
