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
            node.setKeyType(key.getClass());
            Node item = new Node();
            parse(item, null, key);
            node.getKeyFields().add(item);

        }
        if (isBasicClass(object)) {
            node.setValueType(object.getClass());
            node.setValue(object);
            return node;
        }
        node.setValueType(object.getClass());
        node.setValue(object);

        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry next = (Map.Entry) iterator.next();
                Node item = new Node();
                parse(item, next.getKey(), next.getValue());
                node.getValueFields().add(item);
            }
        } else if (object instanceof List) {
            for (Object one : (List) object) {
                Node item = new Node();
                parse(item, null, one);
                node.getValueFields().add(item);
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
                node.getValueFields().add(item);
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
        for (Node item : node.getValueFields()) {
            setValueNull(item);
        }
    }

    private boolean isBasicClass(Object object) {
        if (CLASS.contains(object.getClass())) {
            return true;
        }
        return false;
    }
    private boolean isBasicClass(Class<?> clazz) {
        if (CLASS.contains(clazz)) {
            return true;
        }
        return false;
    }

    private String getGetMethod(String filedName) {
        String method = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        return "get" + method;
    }

    private String getSetMethod(String filedName) {
        String method = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        return "set" + method;
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

    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (Map.class.isAssignableFrom(node.getValueType())) {
            Map map = (Map) node.getValueType().newInstance();
            for (Node field : node.getValueFields()) {
                Object key = build(field.getKeyFields().get(0));
                map.put(key, build(field));
            }
            return map;
        } else if (List.class.isAssignableFrom(node.getValueType())) {
            List list = (List) node.getValueType().newInstance();
            for (Node item : node.getValueFields()) {
                list.add(build(item));
            }
            return list;
        } else if(isBasicClass(node.getValueType())){
            return node.getValue();
        }else {
            Object object = node.getValueType().newInstance();
            for (Node field : node.getValueFields()) {
                Method method = getMethod(node, field, field.getValueType());
                method.invoke(object, build(field));
            }
            return object;
        }
    }

    private Method getMethod(Node node, Node field, Class<?> paramType) throws NoSuchMethodException {
        try {
            return node.getValueType().getMethod(getSetMethod(field.getKey().toString()), paramType);
        } catch (NoSuchMethodException e) {
            Class<?>[] interfaces = paramType.getInterfaces();
            for (Class<?> inter : interfaces) {
                return getMethod(node, field, inter);
            }
            Class<?> superclass = paramType.getSuperclass();
            if (null != superclass) {
                return getMethod(node, field, superclass);
            }
            throw e;
        }

    }

}
