package com.serializer.parse;

import com.serializer.node.*;
import com.serializer.utils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ClassParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassParser.class);

    @Override
    public Node parse(Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> oClass = object.getClass();
        if (Map.class.isAssignableFrom(oClass)) {
            MapNode mapNode = new MapNode();
            return mapNode.parseSelf(object, this);
        } else if (List.class.isAssignableFrom(oClass)) {
            ListNode listNode = new ListNode();
            return listNode.parseSelf(object, this);
        } else if (MethodUtils.isBasicClass(oClass)) {
            BasicClassNode basicClassNode = new BasicClassNode();
            return basicClassNode.parseSelf(object, this);
        } else {
            ClassNode classNode = new ClassNode();
            return classNode.parseSelf(object, this);
        }
    }
}
