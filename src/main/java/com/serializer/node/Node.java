package com.serializer.node;

import com.serializer.parse.Parser;

import java.lang.reflect.InvocationTargetException;

public interface Node {

    Class<?> getType();

    void setType(Class<?> type);

    Node parseSelf(Object object, Parser parser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
