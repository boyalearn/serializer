package com.serializer.utils;

import com.serializer.entity.ClassField;
import com.serializer.node.Node;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public final class MethodUtils {
    public static String buildGetMethodName(String filedName) {
        return "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
    }
    public static String buildSetMethodName(String filedName) {
        return "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
    }

    public static Method getMethod(Node node, ClassField field, Class<?> paramType) throws NoSuchMethodException {
        try {
            return node.getType().getDeclaredMethod(buildSetMethodName((field.getName())),paramType);
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

    private static Set<Class<?>> CLASS = new HashSet<Class<?>>();

    static {
        CLASS.add(String.class);
        CLASS.add(Integer.class);
        CLASS.add(Long.class);
        CLASS.add(Double.class);
        CLASS.add(int.class);
        CLASS.add(double.class);
        CLASS.add(long.class);
        CLASS.add(BigDecimal.class);
    }

    public static boolean isBasicClass(Class<?> clazz) {
        if (CLASS.contains(clazz)) {
            return true;
        }
        return false;
    }
}
