package com.serializer.parse;

import com.serializer.node.Node;

import java.lang.reflect.InvocationTargetException;

public interface Builder {

    Object build(Node node) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
