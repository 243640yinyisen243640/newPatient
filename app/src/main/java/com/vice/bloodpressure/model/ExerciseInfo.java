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

    public ExerciseInfo(String need, String time, String have) {
        this.need = need;
        this.time = time;
        this.have = have;
    }



    private List<ExerciseChildInfo> list;

    public List<ExerciseChildInfo> getList() {
        return list;
    }

    public void setList(List<ExerciseChildInfo> list) {
        this.list = list;
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
}
