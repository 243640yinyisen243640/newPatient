package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class HospitalInfo implements Serializable {
    private String img;
    private String name;
    private String introduce;
    private String location;
    private String level;
    private String office;

    private String isCheck;

    private String doctorImg;
    private String doctorName;
    private String doctorPost;
    private String doctorIntroduce;

    public HospitalInfo(String doctorImg, String doctorName, String doctorPost, String doctorIntroduce) {
        this.doctorImg = doctorImg;
        this.doctorName = doctorName;
        this.doctorPost = doctorPost;
        this.doctorIntroduce = doctorIntroduce;
    }

    public HospitalInfo(String office, String isCheck) {
        this.office = office;
        this.isCheck = isCheck;
    }

    public HospitalInfo(String img, String name, String introduce, String location, String level) {
        this.img = img;
        this.name = name;
        this.introduce = introduce;
        this.location = location;
        this.level = level;
    }

    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
