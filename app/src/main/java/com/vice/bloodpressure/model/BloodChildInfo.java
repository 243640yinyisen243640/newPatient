package com.vice.bloodpressure.model;

import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class BloodChildInfo {
    /**
     * 日期
     */
    private String date;


    private List<BloodThirdInfo> value;

    public List<BloodThirdInfo> getValue() {
        return value;
    }

    public void setValue(List<BloodThirdInfo> value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
