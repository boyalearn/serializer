package com.serializer.parser;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Class<?> keyClazz;

    private Object key;

    private Class<?> valueClazz;

    private Object value;

    private List<Node> fields = new ArrayList<>();

    public Class<?> getKeyClazz() {
        return keyClazz;
    }

    public void setKeyClazz(Class<?> keyClazz) {
        this.keyClazz = keyClazz;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Class<?> getValueClazz() {
        return valueClazz;
    }

    public void setValueClazz(Class<?> valueClazz) {
        this.valueClazz = valueClazz;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public List<Node> getFields() {
        return fields;
    }

    public void setFields(List<Node> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "{keyClazz=" + (null == keyClazz ? "null" : keyClazz.getCanonicalName()) +
                ", key=" + key +
                ", valueClazz=" + (null == valueClazz ? "null" : valueClazz.getCanonicalName()) +
                ", value=" + value +
                ", fields=" + fields +
                '}';
    }
}
