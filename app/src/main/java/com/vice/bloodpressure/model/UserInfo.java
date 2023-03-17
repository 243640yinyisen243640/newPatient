package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserInfo implements Serializable {
    private String userID;
    private String diseaseName;
    private boolean checkID;
    private boolean isSelected;

    private String time;
    private String type;
    private String img;

    public UserInfo() {
    }

    public UserInfo(String diseaseName, String time, String type, String img) {
        this.diseaseName = diseaseName;
        this.time = time;
        this.type = type;
        this.img = img;
    }

    public UserInfo(String userID, String diseaseName) {
        this.userID = userID;
        this.diseaseName = diseaseName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isCheckID() {
        return checkID;
    }

    public void setCheckID(boolean checkID) {
        this.checkID = checkID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
