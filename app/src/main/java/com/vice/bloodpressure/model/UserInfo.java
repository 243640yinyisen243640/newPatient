package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class UserInfo implements Serializable {
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
    private String archives_id;
    /**
     * userId
     */
    private String user_id;
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
    /**
     * 是否完善个人信息  1完善 2未完善
     */
    private String info_status;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * bmi数值
     */
    private String bmi;
    /**
     * bmi状态
     * 1 偏瘦
     * 2 正常
     * 3 超重
     * 4 肥胖
     */
    private String bmiStatus;
    /**
     * bmi文字标签
     */
    private String bmiTag;
    /**
     * bmi正常范围文案
     */
    private String bmiNormal;
    /**
     * 收缩压
     */
    private String sbp;
    /**
     * 身高
     */
    private String dbp;
    /**
     * 舒张压
     */
    private String bpDate;
    /**
     * 血压测量日期
     */
    private String bpStatus;
    /**
     * 血压状态
     * 1 偏低
     * 2 正常
     * 3 偏高
     */
    private String bgValue;
    /**
     * 血糖测量日期
     */
    private String bgDate;
    /**
     * 血糖状态
     * 1 偏低
     * 2 正常
     * 3 偏高
     */
    private String bgStatus;
    /**
     * 心率
     */
    private String hr;
    /**
     * 心率状态
     * 1 偏低
     * 2 正常
     * 3 偏低
     */
    private String hrStatus;
    /**
     * 心率检测日期
     */
    private String hrDate;
    /**
     * 肾脏病变
     * true 是
     * false 否
     */
    private String dn;
    /**
     * 视网膜病变
     */
    private String dr;
    /**
     * 神经病变
     */
    private String dpn;
    /**
     * 下肢血管病变
     */
    private String lead;
    /**
     * 糖尿病足
     */
    private String df;

    public UserInfo() {
    }

    public UserInfo(String diseaseName, String time, String type, String img) {
        this.diseaseName = diseaseName;
        this.time = time;
        this.type = type;
        this.img = img;
    }

    public UserInfo(String userID, String diseaseName) {
        this.user_id = userID;
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


    public String getArchivesId() {
        return archives_id;
    }

    public void setArchivesId(String archivesId) {
        this.archives_id = archivesId;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
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

    public String getInfo_status() {
        return info_status;
    }

    public void setInfo_status(String info_status) {
        this.info_status = info_status;
    }

    public String getArchives_id() {
        return archives_id;
    }

    public void setArchives_id(String archives_id) {
        this.archives_id = archives_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
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

    public String getBmiNormal() {
        return bmiNormal;
    }

    public void setBmiNormal(String bmiNormal) {
        this.bmiNormal = bmiNormal;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getDbp() {
        return dbp;
    }

    public void setDbp(String dbp) {
        this.dbp = dbp;
    }

    public String getBpDate() {
        return bpDate;
    }

    public void setBpDate(String bpDate) {
        this.bpDate = bpDate;
    }

    public String getBpStatus() {
        return bpStatus;
    }

    public void setBpStatus(String bpStatus) {
        this.bpStatus = bpStatus;
    }

    public String getBgValue() {
        return bgValue;
    }

    public void setBgValue(String bgValue) {
        this.bgValue = bgValue;
    }

    public String getBgDate() {
        return bgDate;
    }

    public void setBgDate(String bgDate) {
        this.bgDate = bgDate;
    }

    public String getBgStatus() {
        return bgStatus;
    }

    public void setBgStatus(String bgStatus) {
        this.bgStatus = bgStatus;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getHrStatus() {
        return hrStatus;
    }

    public void setHrStatus(String hrStatus) {
        this.hrStatus = hrStatus;
    }

    public String getHrDate() {
        return hrDate;
    }

    public void setHrDate(String hrDate) {
        this.hrDate = hrDate;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getDpn() {
        return dpn;
    }

    public void setDpn(String dpn) {
        this.dpn = dpn;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }
}
