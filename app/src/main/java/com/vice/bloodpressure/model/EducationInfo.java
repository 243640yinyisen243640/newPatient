package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationInfo implements Serializable {
    private String bg;
    private String title;
    private String content;
    private String state;
    private String subject;
    private List<EducationInfo> childList;
    private List<EducationInfo> list;

    private String classifyId;
    /**
     * 首页用到的文章字数
     */
    private String wordNumber;
    /**
     * 首页用到的阅读时间
     */
    private String readTime;
    /**
     * 首页文章名字
     */
    private String essayName;
    /**
     * 文章简介
     */
    private String essayProfile;
    /**
     *系列名字
     */
    private String sname;
    /**
     *文章名字
     */
    private String sid;

    /**
     * 文章id
     */
    private String essayId;

    private List<EducationInfo> teachTypeDomains;

    public List<EducationInfo> getTeachTypeDomains() {
        return teachTypeDomains;
    }

    public void setTeachTypeDomains(List<EducationInfo> teachTypeDomains) {
        this.teachTypeDomains = teachTypeDomains;
    }

    public String getEssayProfile() {
        return essayProfile;
    }

    public void setEssayProfile(String essayProfile) {
        this.essayProfile = essayProfile;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId;
    }

    public String getEssayName() {
        return essayName;
    }

    public void setEssayName(String essayName) {
        this.essayName = essayName;
    }

    public String getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(String wordNumber) {
        this.wordNumber = wordNumber;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public EducationInfo(String title, String state) {
        this.title = title;
        this.state = state;
    }

    public EducationInfo(String bg, String title, String content, String state, String subject) {
        this.bg = bg;
        this.title = title;
        this.content = content;
        this.state = state;
        this.subject = subject;
    }

    public EducationInfo(String title, String content, String classifyId) {
        this.title = title;
        this.content = content;
        this.classifyId = classifyId;
    }

    /**
     * 那个按钮的展示状态 0展开 1收起状态，展开更多
     */
    private int isExpand = 1;

    private String isCheck;

    /**
     * 智能教育 类型名称
     */
    private String typeName;
    private String typeId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public List<EducationInfo> getList() {
        return list;
    }

    public void setList(List<EducationInfo> list) {
        this.list = list;
    }

    public int getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(int isExpand) {
        this.isExpand = isExpand;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<EducationInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<EducationInfo> childList) {
        this.childList = childList;
    }
}
