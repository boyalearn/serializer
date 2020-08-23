package com.serializer.token;

import com.serializer.node.BasicClassNode;
import com.serializer.node.Node;
import com.serializer.parse.Builder;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class BasicClassToken implements Token {

    private Builder builder;

    public BasicClassToken(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        BasicClassNode basicClassNode = (BasicClassNode) node;
        Class<?> type = basicClassNode.getType();
        if (type == Date.class) {
            return new Date(Long.valueOf(String.valueOf(basicClassNode.getValue())));
        } else {
            return basicClassNode.getValue();
        }
    }
}
