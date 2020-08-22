package com.test;

import com.serializer.parser.Node;
import com.serializer.parser.Parser;
import com.test.jackson.Auth;
import com.test.jackson.MapBean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        MapBean mapBean = new MapBean("");
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        List<String> list1 = new ArrayList<>();
        list1.add("keyOne1");
        list1.add("keyTwo1");
        List<String> list2 = new ArrayList<>();
        list2.add("keyOne2");
        list2.add("key\"Two2");
        map.put("list1", new Auth("one", "two"));
        map.put("list2", new Auth("one", "two"));

        mapBean.setMap(map);
        Parser parser = new Parser();
        Node parse = parser.parse(mapBean);
        System.out.println(parse);
    }
}
