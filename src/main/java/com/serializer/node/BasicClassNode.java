package com.serializer.node;

import com.serializer.parse.Parser;

public class BasicClassNode extends AbstractNode {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public Node parseSelf(Object object, Parser parser) {
        setType(object.getClass());
        setValue(object);
        return this;
    }
}
