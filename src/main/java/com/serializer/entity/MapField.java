package com.serializer.entity;

import com.serializer.node.Node;

public class MapField {

    private Node key;
    private Node value;

    public MapField() {
    }

    public MapField(Node key, Node value) {
        this.key = key;
        this.value = value;
    }

    public Node getKey() {
        return key;
    }

    public void setKey(Node key) {
        this.key = key;
    }

    public Node getValue() {
        return value;
    }

    public void setValue(Node value) {
        this.value = value;
    }
}
