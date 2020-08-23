package com.serializer.token;

import com.serializer.node.Node;

import java.lang.reflect.InvocationTargetException;

public interface Token {
    Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
