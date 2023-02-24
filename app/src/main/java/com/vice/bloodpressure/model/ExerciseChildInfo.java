package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ExerciseChildInfo implements Serializable {
    private String type;
    private String time;
    private String exerciseTime;
    private String onceFire;
    private String state;

    public ExerciseChildInfo(String type, String exerciseTime, String onceFire) {
        this.type = type;
        this.exerciseTime = exerciseTime;
        this.onceFire = onceFire;
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

    public String getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

}
