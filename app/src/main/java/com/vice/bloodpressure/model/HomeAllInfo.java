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
    /**
     * 健康贴士是否开启 1开启 0不开启
     */
    private String healthyStatus;
    /**
     * 个人信息是否开启 1开启 0不开启
     */
    private String basicInfoStatus;
    /**
     * 血糖是否开启
     */
    private String bgModuleStatus;
    /**
     * 血压是否开启
     */
    private String bpModuleStatus;
    /**
     * BMI是否开启
     */
    private String bmiModuleStatus;
    /**
     * 智能饮食是否开启  1开启 0不开启
     */
    private String dietModuleStatus;
    /**
     * 智能运动是否开启 1开启 0不开启
     */
    private String sportModuleStatus;
    /**
     * 智能教育是否开启 1开启 0不开启
     */
    private String todayArticleModuleStatus;

    public String getHealthyStatus() {
        return healthyStatus;
    }

    public void setHealthyStatus(String healthyStatus) {
        this.healthyStatus = healthyStatus;
    }

    public String getBasicInfoStatus() {
        return basicInfoStatus;
    }

    public void setBasicInfoStatus(String basicInfoStatus) {
        this.basicInfoStatus = basicInfoStatus;
    }

    public String getBgModuleStatus() {
        return bgModuleStatus;
    }

    public void setBgModuleStatus(String bgModuleStatus) {
        this.bgModuleStatus = bgModuleStatus;
    }

    public String getBpModuleStatus() {
        return bpModuleStatus;
    }

    public void setBpModuleStatus(String bpModuleStatus) {
        this.bpModuleStatus = bpModuleStatus;
    }

    public String getBmiModuleStatus() {
        return bmiModuleStatus;
    }

    public void setBmiModuleStatus(String bmiModuleStatus) {
        this.bmiModuleStatus = bmiModuleStatus;
    }

    public String getDietModuleStatus() {
        return dietModuleStatus;
    }

    public void setDietModuleStatus(String dietModuleStatus) {
        this.dietModuleStatus = dietModuleStatus;
    }

    public String getSportModuleStatus() {
        return sportModuleStatus;
    }

    public void setSportModuleStatus(String sportModuleStatus) {
        this.sportModuleStatus = sportModuleStatus;
    }

    public String getTodayArticleModuleStatus() {
        return todayArticleModuleStatus;
    }

    public void setTodayArticleModuleStatus(String todayArticleModuleStatus) {
        this.todayArticleModuleStatus = todayArticleModuleStatus;
    }

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
