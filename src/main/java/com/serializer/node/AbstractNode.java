package com.serializer.node;

public abstract class AbstractNode implements Node {

    private Class<?> type;

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public void setType(Class<?> type) {
        this.type = type;
    }
}
