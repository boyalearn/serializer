package com.serializer.node;

import com.serializer.parse.Parser;

import java.util.Date;

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
        Class<?> aClass = object.getClass();
        setType(aClass);
        if(Date.class==aClass){
            setValue(((Date)object).getTime());
        }else {
            setValue(object);
        }
        return this;
    }
}
