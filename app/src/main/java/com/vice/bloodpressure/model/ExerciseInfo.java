package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseInfo implements Serializable {
    private String need;
    private String time;
    private String fire;
    private String type;
    private String exerciseTime;
    private String have;
    private String onceFire;
    /**
     *  1偏瘦 2正常 3超重 4肥胖
     */
    private String bmiStatus;
    /**
     * 1偏瘦 2正常 3超重 4肥胖
     */
    private String bmiTag;
    /**
     * 超重斤数
     */
    private String weight;
    /**
     * 今日所需热量
     */
    private String needConsumeCalories;
    /**
     * 已消耗热量
     */
    private String consumeCalories;
    /**
     * 跑步消耗热量
     */
    private String runCalories;
    /**
     * 步行消耗热量
     */
    private String walkCalories;
    /**
     * 未消耗
     */
    private String notConsumed;
    /**
     * 其他运动消耗
     */
    private String otherSportConsumed;
    /**
     * 有氧运动
     */
    private ExerciseChildInfo sportAerobics;
    /**
     * 抗阻运动
     */
    private ExerciseChildInfo sportResistance;
    /**
     * 柔韧性运动
     */
    private ExerciseChildInfo sportPliable;
    /**
     * 步数
     */
    private String walkNum;
    /**
     * 有氧运动名称
     */
    private String aerobicsName;
    /**
     * 有氧运动需消耗热量
     */
    private String aerobicsNeedCalories;
    /**
     * 有氧运动需要时长
     */
    private String aerobicsTime;
    /**
     * 方案Id
     */
    private String sportPlanId;
    /**
     * 抗住运动ID
     */
    private String resistanceId;
    /**
     * 柔韧性运动ID
     */
    private String pliableId;
    /**
     * 有氧运动id
     */
    private String aerobicsId;
    private List<ExerciseChildInfo> ards;
    /**
     * 运动日期
     */
    private String sportDate;
    /**
     * 消耗热量
     */
    private String caloriesConsumed;

    public String getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(String caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public String getSportDate() {
        return sportDate;
    }

    public void setSportDate(String sportDate) {
        this.sportDate = sportDate;
    }

    public String getAerobicsId() {
        return aerobicsId;
    }

    public void setAerobicsId(String aerobicsId) {
        this.aerobicsId = aerobicsId;
    }

    public String getWalkNum() {
        return walkNum;
    }

    public void setWalkNum(String walkNum) {
        this.walkNum = walkNum;
    }

    public String getAerobicsName() {
        return aerobicsName;
    }

    public void setAerobicsName(String aerobicsName) {
        this.aerobicsName = aerobicsName;
    }

    public String getAerobicsNeedCalories() {
        return aerobicsNeedCalories;
    }

    public void setAerobicsNeedCalories(String aerobicsNeedCalories) {
        this.aerobicsNeedCalories = aerobicsNeedCalories;
    }

    public String getAerobicsTime() {
        return aerobicsTime;
    }

    public void setAerobicsTime(String aerobicsTime) {
        this.aerobicsTime = aerobicsTime;
    }

    public String getSportPlanId() {
        return sportPlanId;
    }

    public void setSportPlanId(String sportPlanId) {
        this.sportPlanId = sportPlanId;
    }

    public String getResistanceId() {
        return resistanceId;
    }

    public void setResistanceId(String resistanceId) {
        this.resistanceId = resistanceId;
    }

    public String getPliableId() {
        return pliableId;
    }

    public void setPliableId(String pliableId) {
        this.pliableId = pliableId;
    }




    public List<ExerciseChildInfo> getArds() {
        return ards;
    }

    public void setArds(List<ExerciseChildInfo> ards) {
        this.ards = ards;
    }

    public String getOnceFire() {
        return onceFire;
    }

    public void setOnceFire(String onceFire) {
        this.onceFire = onceFire;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public String getHave() {
        return have;
    }

    public void setHave(String have) {
        this.have = have;
    }

    public String getBmiStatus() {
        return bmiStatus;
    }

    public void setBmiStatus(String bmiStatus) {
        this.bmiStatus = bmiStatus;
    }

    public String getBmiTag() {
        return bmiTag;
    }

    public void setBmiTag(String bmiTag) {
        this.bmiTag = bmiTag;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getNeedConsumeCalories() {
        return needConsumeCalories;
    }

    public void setNeedConsumeCalories(String needConsumeCalories) {
        this.needConsumeCalories = needConsumeCalories;
    }

    public String getConsumeCalories() {
        return consumeCalories;
    }

    public void setConsumeCalories(String consumeCalories) {
        this.consumeCalories = consumeCalories;
    }

    public String getRunCalories() {
        return runCalories;
    }

    public void setRunCalories(String runCalories) {
        this.runCalories = runCalories;
    }

    public String getWalkCalories() {
        return walkCalories;
    }

    public void setWalkCalories(String walkCalories) {
        this.walkCalories = walkCalories;
    }

    public String getNotConsumed() {
        return notConsumed;
    }

    public void setNotConsumed(String notConsumed) {
        this.notConsumed = notConsumed;
    }

    public String getOtherSportConsumed() {
        return otherSportConsumed;
    }

    public void setOtherSportConsumed(String otherSportConsumed) {
        this.otherSportConsumed = otherSportConsumed;
    }

    public ExerciseChildInfo getSportAerobics() {
        return sportAerobics;
    }

    public void setSportAerobics(ExerciseChildInfo sportAerobics) {
        this.sportAerobics = sportAerobics;
    }

    public ExerciseChildInfo getSportResistance() {
        return sportResistance;
    }

    public void setSportResistance(ExerciseChildInfo sportResistance) {
        this.sportResistance = sportResistance;
    }

    public ExerciseChildInfo getSportPliable() {
        return sportPliable;
    }

    public void setSportPliable(ExerciseChildInfo sportPliable) {
        this.sportPliable = sportPliable;
    }
}
