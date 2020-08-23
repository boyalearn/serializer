package com.serializer.node;

import com.serializer.parse.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ListNode extends AbstractNode {
    private List<Node> fields = new ArrayList<Node>();

    public List<Node> getFields() {
        return fields;
    }

    public void setFields(List<Node> fields) {
        this.fields = fields;
    }


    @Override
    public Node parseSelf(Object object, Parser parser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> oClass = object.getClass();
        setType(oClass);
        List list = (List) object;
        for (Object item : list) {
            getFields().add(parser.parse(item));
        }
        return this;
    }
}
