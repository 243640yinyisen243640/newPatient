package com.vice.bloodpressure.version;

public class CheckVersionModel {
    /**
     * int	是否必须更新【0：否，1：是】
     * */
    private String isMustUpdate;
    /**
     * 更新时间
     * */
    private String updateTime;
    /**
     * 下载地址
     * */
    private String itunesUrl;
    /**
     *更新内容
     * */
    private String updateContent;
    /**
     * string	版本号
     */
    private String versionNum;
    /**
     * string	版本名称
     */
    private String versionName;

    public String getIsMustUpdate() {
        return isMustUpdate;
    }

    public void setIsMustUpdate(String isMustUpdate) {
        this.isMustUpdate = isMustUpdate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getItunesUrl() {
        return itunesUrl;
    }

    public void setItunesUrl(String itunesUrl) {
        this.itunesUrl = itunesUrl;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
