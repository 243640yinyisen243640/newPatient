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

    private boolean isCheck;

    /**
     * 档案ID
     */
    private String archivesId;
    /**
     * userId
     */
    private String userId;
    /**
     * 授权码
     */
    private String access_token;
    /**
     * 过期时间
     */
    private String expires_in;
    /**
     * 用户类型(00系统,01用户 02医生 03护士 04主任 05 科室 06医院）
     */
    private String user_type;

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

    public String getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(String archivesId) {
        this.archivesId = archivesId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
