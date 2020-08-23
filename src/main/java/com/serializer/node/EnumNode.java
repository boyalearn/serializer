package com.serializer.node;

import com.serializer.parse.Parser;

import java.lang.reflect.InvocationTargetException;

public class EnumNode extends AbstractNode {

    private String enumName;

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    @Override
    public Node parseSelf(Object object, Parser parser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> oClass = object.getClass();
        setType(oClass);
        setEnumName(((Enum) object).name());
        return this;
    }
}
