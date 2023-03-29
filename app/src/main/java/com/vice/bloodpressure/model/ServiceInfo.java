package com.vice.bloodpressure.model;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class ServiceInfo {
    private String time;
    private String data;
    private String rate;
    private String type;

    public ServiceInfo(String time, String data, String type) {
        this.time = time;
        this.data = data;
        this.type = type;
    }

    public ServiceInfo(String time, String data, String rate, String type) {
        this.time = time;
        this.data = data;
        this.rate = rate;
        this.type = type;
    }

    public ServiceInfo(String time, String data) {
        this.time = time;
        this.data = data;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
