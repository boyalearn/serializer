package com.test.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapBean {


    public MapBean() {
    }

    public MapBean(String json) {
        this.map = new LinkedHashMap<>();
    }
    @JsonDeserialize(using = MyDeserialize.class)
    private Map<String, Object> map = new LinkedHashMap<String, Object>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
