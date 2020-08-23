package com.serializer.entity;

import com.serializer.node.Node;

public class ClassField {

    private String name;

    private Node value;

    public ClassField() {
    }

    public ClassField(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }
}
