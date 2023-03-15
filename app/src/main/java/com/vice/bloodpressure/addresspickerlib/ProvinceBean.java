package com.vice.bloodpressure.addresspickerlib;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wepon on 2017/12/4.
 * 数据模型
 */

public class ProvinceBean implements Serializable {
    private String name;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "name='" + name + '\'' +
                ", city=" + city +
                '}';
    }

    public static class CityBean implements Serializable {
        private String name;
        private List<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

        @Override
        public String toString() {
            return "CityBean{" +
                    "name='" + name + '\'' +
                    ", area=" + area +
                    '}';
        }
    }
}
