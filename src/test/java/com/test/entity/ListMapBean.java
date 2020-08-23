package com.test.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListMapBean {
    private Map<List<String>, Object> map = new LinkedHashMap<List<String>, Object>();
    public Map<List<String>, Object> getMap() {
        return map;
    }

    public void setMap(Map<List<String>, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "ListMapBean{" +
                "map=" + map +
                '}';
    }
}
