package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseChildInfo implements Serializable {
    private String time;
    private String sportTime;
    private String onceFire;
    private String state;
    /**
     * 推荐运动
     */
    private String id;
    /**
     * 项目
     */
    private String sportName;
    /**
     * 运动项目消耗每分的热量
     */
    private String calorie;
    /**
     * 体重
     */
    private String weight;
    /**
     * 运动目的
     */
    private String purpose;
    /**
     * 注意事项
     */
    private String note;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 审核页状态
     */
    private String menuCheckStatus;
    /**
     * 动作要领
     */
    private String ability;
    /**
     * 运动时长
     */
    private String workouts;
    /**
     * 运动频次
     */
    private String frequency;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 视频
     */
    private String videoUrl;
    /**
     * type
     */
    private String type;
    /**
     * 消耗热量
     */
    private String consumeCalories;
    /**
     * 达标状态 1未达标  2达标 3超标
     */
    private String sportStatus;
    /**
     * 运动数量
     */
    private String sportNum;

    public String getSportNum() {
        return sportNum;
    }

    public void setSportNum(String sportNum) {
        this.sportNum = sportNum;
    }

    public String getSportStatus() {
        return sportStatus;
    }

    public void setSportStatus(String sportStatus) {
        this.sportStatus = sportStatus;
    }

    public String getConsumeCalories() {
        return consumeCalories;
    }

    public void setConsumeCalories(String consumeCalories) {
        this.consumeCalories = consumeCalories;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public ExerciseChildInfo(String type, String time, String onceFire, String state) {
        this.type = type;
        this.time = time;
        this.onceFire = onceFire;
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOnceFire() {
        return onceFire;
    }

    public void setOnceFire(String onceFire) {
        this.onceFire = onceFire;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSportTime() {
        return sportTime;
    }

    public void setSportTime(String sportTime) {
        this.sportTime = sportTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMenuCheckStatus() {
        return menuCheckStatus;
    }

    public void setMenuCheckStatus(String menuCheckStatus) {
        this.menuCheckStatus = menuCheckStatus;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getWorkouts() {
        return workouts;
    }

    public void setWorkouts(String workouts) {
        this.workouts = workouts;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
