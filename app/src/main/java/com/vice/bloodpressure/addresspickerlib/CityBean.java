package com.vice.bloodpressure.addresspickerlib;

import com.contrarywind.interfaces.IPickerViewData;

public class CityBean implements IPickerViewData {
    private String nodeName;

    public CityBean(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String getPickerViewText() {
        return nodeName;
    }
}