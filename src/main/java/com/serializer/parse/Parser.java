package com.serializer.parse;


import com.serializer.node.Node;

import java.lang.reflect.InvocationTargetException;

public interface Parser {

    Node parse(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
