package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EquipmetInfo {
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 设备id
     */
    private String brandId;
    /**
     * 设备id
     */
    private String id;
    /**
     * 设备名称
     */
    private String brandName;
    /**
     * 设备号
     */
    private String deviceCode;
    /**
     * 设备分类1->血糖仪;2->血压计;
     */
    private String deviceCategory;
    /**
     * 1.启用 2.已绑定 3.禁用
     */
    private String status;
    /**
     * 创建时间/绑定时间
     */
    private String createTime;
    /**
     * 图标
     */
    private String brandImg;
    /**
     * 关系id
     */
    private String pkId;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
