package com.serializer.node;

import com.serializer.entity.MapField;
import com.serializer.parse.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MapNode extends AbstractNode {

    List<MapField> fields=new ArrayList<MapField>();

    public List<MapField> getFields() {
        return fields;
    }

    public void setFields(List<MapField> fields) {
        this.fields = fields;
    }

    @Override
    public Node parseSelf(Object object, Parser parser) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> oClass = object.getClass();
        setType(oClass);
        Iterator iterator = ((Map) object).entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            Object key = next.getKey();
            Object value = next.getValue();
            getFields().add(new MapField(parser.parse(key), parser.parse(value)));
        }
        return this;
    }
}
