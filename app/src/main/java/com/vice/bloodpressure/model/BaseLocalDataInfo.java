package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class BaseLocalDataInfo {
    private String name;
    private String id;
    private boolean isCheck;
    /**
     * 运动名称
     */
    private String sportName;
    /**
     * 运动项目消耗每分的热量
     */
    private String calorie;
    /**
     * 0已经存在过  1没存在过
     */
    private String isSelect;

    private Class className;

    private int page;

    public BaseLocalDataInfo(String name, String id, String isSelect) {
        this.name = name;
        this.id = id;
        this.isSelect = isSelect;
    }

    public BaseLocalDataInfo(String name, String id,Class className,int page) {
        this.name = name;
        this.id = id;
        this.className = className;
        this.page = page;
    }

    public BaseLocalDataInfo(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }
}
