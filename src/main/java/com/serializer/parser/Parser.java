package com.serializer.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

public class Parser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public Node parse(Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Node root = new Node();
        return parse(root, null, object);
    }

    private Node parse(Node node, Object key, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (null == object) {
            return node;
        }
        if (null != key) {
            node.setKey(key);
            node.setKeyClazz(key.getClass());
        }
        if (isBasicClass(object)) {
            node.setValueClazz(object.getClass());
            node.setValue(object);
            return node;
        }
        node.setValueClazz(object.getClass());
        node.setValue(object);

        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                Node item = new Node();
                parse(item, next.getKey(), next.getValue());
                node.getFields().add(item);
            }
        } else if (object instanceof List) {
            for (Object one : (List) object) {
                Node item = new Node();
                parse(item, null, one);
                node.getFields().add(item);
            }
        } else {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                Node item = new Node();
                LOGGER.info(object.getClass().getName());
                LOGGER.info(field.getName());
                LOGGER.info(object.toString());
                Method method = object.getClass().getMethod(getGetMethod(field.getName()));
                Object invoke = method.invoke(object);
                parse(item, field.getName(), invoke);
                node.getFields().add(item);
            }
        }
        setValueNull(node);
        return node;
    }

    private void setValueNull(Node node) {
        if (null == node || null == node.getValue()) {
            return;
        }
        if (!isBasicClass(node.getValue())) {
            node.setValue(null);
        }
        for (Node item : node.getFields()) {
            setValueNull(item);
        }
    }

    private boolean isBasicClass(Object object) {
        if (CLASS.contains(object.getClass())) {
            return true;
        }
        return false;
    }

    private String getGetMethod(String filedName) {
        String method = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        return "get" + method;
    }

    private static Set<Class<?>> CLASS = new HashSet<Class<?>>();

    {
        CLASS.add(String.class);
        CLASS.add(Integer.class);
        CLASS.add(Long.class);
        CLASS.add(Double.class);
        CLASS.add(int.class);
        CLASS.add(double.class);
        CLASS.add(long.class);
        CLASS.add(BigDecimal.class);
    }

    private static Set<Character> opChar = new HashSet<>();

    {
        opChar.add('{');
        opChar.add('}');

        opChar.add('[');

        opChar.add(']');

        opChar.add('=');
        opChar.add(',');

    }

    public Object build(String json) {
        Stack<Character> opStack = new Stack<>();
        Stack<Character> odStack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        char[] chars = json.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            odStack.push(chars[i]);
            if ('{' == chars[i]) {
                opStack.add(chars[i]);

            } else if ('}' == chars[i]) {
                opStack.pop();
            } else if('['==chars[i]){
                opStack.add(chars[i]);
            } else if(']'==chars[i]){
                opStack.pop();
                String str = sb.toString();
                sb.setLength(0);
                LOGGER.info(str);
            }else if ('=' == chars[i]) {
                String str = sb.toString();
                sb.setLength(0);
                LOGGER.info(str);
            } else if (',' == chars[i]) {
                String str = sb.toString();
                sb.setLength(0);
                LOGGER.info(str);
            } else if (' ' == chars[i]) {

            } else {
                sb.append(chars[i]);
            }
        }
        return null;
    }

}
