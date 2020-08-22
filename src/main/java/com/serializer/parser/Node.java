package com.serializer.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Class<?> keyType;

    private Object key;

    private List<Node> keyFields = new ArrayList<>();


    private Class<?> valueType;

    private Object value;

    private List<Node> valueFields = new ArrayList<>();

    public Class<?> getKeyType() {
        return keyType;
    }

    public void setKeyType(Class<?> keyType) {
        this.keyType = keyType;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public List<Node> getKeyFields() {
        return keyFields;
    }

    public void setKeyFields(List<Node> keyFields) {
        this.keyFields = keyFields;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Node> getValueFields() {
        return valueFields;
    }

    public void setValueFields(List<Node> valueFields) {
        this.valueFields = valueFields;
    }
}
