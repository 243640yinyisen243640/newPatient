package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class DoctorInfo {
    /**
     * id
     */
    private String id;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 角色
     */
    private String roleId;
    /**
     * 性别
     */
    private String sex;
    /**
     * 账号
     */
    private String userName;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 系统内部Id
     */
    private String userId;
    /**
     * 所属科室Id
     */
    private String deptId;
    /**
     * 擅长
     */
    private String merit;
    /**
     * 职称
     */
    private String titles;
    /**
     * 简介
     */
    private String profile;
    /**
     * 科室名称
     */
    private String deptName;


    private String isCheck;


    private String doctorPost;
    private String doctorIntroduce;

    /**
     * 医院图标
     */
    private String logo;
    /**
     * 医院名称
     */
    private String name;
    /**
     * 简写
     */
    private String shortName;
    /**
     * 等级  三甲
     */
    private String category;
    /**
     * 地址
     */
    private String detailedAddress;
    /**
     * 简介
     */
    private String introduction;
    /**
     * true已绑定/false未绑定
     */
    private boolean bindExternal;



    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getDoctorPost() {
        return doctorPost;
    }

    public void setDoctorPost(String doctorPost) {
        this.doctorPost = doctorPost;
    }

    public String getDoctorIntroduce() {
        return doctorIntroduce;
    }

    public void setDoctorIntroduce(String doctorIntroduce) {
        this.doctorIntroduce = doctorIntroduce;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMerit() {
        return merit;
    }

    public void setMerit(String merit) {
        this.merit = merit;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    public boolean isBindExternal() {
        return bindExternal;
    }

    public void setBindExternal(boolean bindExternal) {
        this.bindExternal = bindExternal;
    }
}
