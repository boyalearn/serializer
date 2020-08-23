package com.serializer.token;

import com.serializer.entity.ClassField;
import com.serializer.node.ClassNode;
import com.serializer.node.Node;
import com.serializer.parse.Builder;
import com.serializer.utils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassToken implements Token {

    private Builder builder;

    public ClassToken(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object object = node.getType().newInstance();
        ClassNode classNode = (ClassNode) node;
        for (ClassField field : classNode.getFields()) {
            Method method = MethodUtils.getMethod(node, field, field.getValue().getType());
            method.invoke(object, builder.build(field.getValue()));
        }
        return object;
    }
}
