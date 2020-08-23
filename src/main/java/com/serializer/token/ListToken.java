package com.serializer.token;

import com.serializer.node.ListNode;
import com.serializer.node.Node;
import com.serializer.parse.Builder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ListToken implements Token {

    private Builder builder;

    public ListToken(Builder builder) {
        this.builder = builder;
    }

    @Override
    public Object build(Node node) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List list = (List) node.getType().newInstance();
        ListNode listNode = (ListNode) node;
        for (Node item : listNode.getFields()) {
            list.add(builder.build(item));
        }
        return list;
    }
}
