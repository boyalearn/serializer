package com.serializer.parse;

import com.serializer.node.Node;
import com.serializer.token.BasicClassToken;
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

    private BasicClassToken basicClassToken;

    public ClassBuilder() {
        this.mapToken = new MapToken(this);
        this.listToken = new ListToken(this);
        this.classToken = new ClassToken(this);
        this.basicClassToken = new BasicClassToken(this);
    }

    @Override
    public Object build(Node node) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (Map.class.isAssignableFrom(node.getType())) {
            return mapToken.build(node);
        } else if (List.class.isAssignableFrom(node.getType())) {
            return listToken.build(node);
        } else if (MethodUtils.isBasicClass(node.getType())) {
            return basicClassToken.build(node);
        } else {
            return classToken.build(node);
        }
    }
}
