package com.serializer.token;

import com.serializer.node.EnumNode;
import com.serializer.node.Node;
import com.serializer.parse.Builder;

import java.lang.reflect.InvocationTargetException;

public class EnumToken implements Token {

    private Builder builder;

    public EnumToken(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        EnumNode enumNode = (EnumNode) node;
        Class<Enum> type = (Class<Enum>) enumNode.getType();
        return Enum.valueOf(type, enumNode.getEnumName());
    }
}
