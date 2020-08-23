package com.serializer.node;

import com.serializer.entity.ClassField;
import com.serializer.parse.Parser;
import com.serializer.utils.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassNode extends AbstractNode {
    private List<ClassField> fields = new ArrayList<ClassField>();

    public List<ClassField> getFields() {
        return fields;
    }

    public void setFields(List<ClassField> fields) {
        this.fields = fields;
    }

    @Override
    public Node parseSelf(Object object, Parser parser) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> oClass = object.getClass();
        setType(oClass);
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            Method method = object.getClass().getMethod(MethodUtils.buildGetMethodName(field.getName()));
            Object attr = method.invoke(object);
            if (null == attr) continue;
            getFields().add(new ClassField(field.getName(), parser.parse(attr)));
        }
        return this;
    }
}
