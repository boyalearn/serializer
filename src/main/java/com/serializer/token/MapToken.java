package com.serializer.token;

import com.serializer.entity.MapField;
import com.serializer.node.MapNode;
import com.serializer.node.Node;
import com.serializer.parse.Builder;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MapToken implements Token {

    private Builder builder;

    public MapToken(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Map map = (Map) node.getType().newInstance();
        MapNode mapNode = (MapNode) node;
        for (MapField field : mapNode.getFields()) {
            Object key = builder.build(field.getKey());
            map.put(key, builder.build(field.getValue()));
        }
        return map;
    }
}
