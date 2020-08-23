package com.serializer.parse;

import com.serializer.node.BasicClassNode;
import com.serializer.node.Node;
import com.serializer.token.ClassToken;
import com.serializer.token.ListToken;
import com.serializer.token.MapToken;
import com.serializer.utils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class ClassBuilder implements Builder {

    private MapToken mapToken;

    private ListToken listToken;

    private ClassToken classToken;

    public ClassBuilder() {
        this.mapToken = new MapToken(this);
        this.listToken = new ListToken(this);
        this.classToken = new ClassToken(this);
    }

    @Override
    public Object build(Node node) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (Map.class.isAssignableFrom(node.getType())) {
            return mapToken.build(node);
        } else if (List.class.isAssignableFrom(node.getType())) {
            return listToken.build(node);
        } else if (MethodUtils.isBasicClass(node.getType())) {
            return ((BasicClassNode) node).getValue();
        } else {
            return classToken.build(node);
        }
    }
}
