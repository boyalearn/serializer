package com.test.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapBean {


    public MapBean() {
    }

    public MapBean(String json) {
        this.map = new LinkedHashMap<>();
    }
    private Map<String, Object> map = new LinkedHashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "map=" + map +
                '}';
    }
}
