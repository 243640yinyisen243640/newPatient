package com.vice.bloodpressure.model;

/**
 * 作者: LYY
 * 类名:
 * 传参:
 * 描述:版本检测更新
 */
public class CheckVersionInfo {


    /**
     * isNewest : false
     * fileUrl : http://43.138.250.233:9000//
     */

    private String isNewest;
    private String fileUrl;

    public String getIsNewest() {
        return isNewest;
    }

    public void setIsNewest(String isNewest) {
        this.isNewest = isNewest;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
