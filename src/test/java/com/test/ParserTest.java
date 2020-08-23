package com.test;

import com.serializer.node.Node;
import com.serializer.parse.ClassBuilder;
import com.serializer.parse.ClassParser;
import com.serializer.utils.JsonUtils;
import com.test.entity.Auth;
import com.test.entity.ListMapBean;
import com.test.entity.MapBean;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ParserTest {

    @Test
    public void testMap() {
        MapBean mapBean = new MapBean("");
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        LinkedHashMap<List<String>, Object> listMap = new LinkedHashMap<List<String>, Object>();

        map.put("key1", new Auth("one", "two"));
        map.put("key2", new Auth("one", "two"));
        mapBean.setMap(map);
        ClassParser parser = new ClassParser();
        ClassBuilder builder = new ClassBuilder();
        try {
            Node node = parser.parse(mapBean);
            Object build = builder.build(node);
            System.out.println(mapBean.toString());
            System.out.println(build.toString());
            System.out.println(JsonUtils.getJson(node));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapKeyList() {
        ListMapBean listMapBean = new ListMapBean();
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        LinkedHashMap<List<String>, Object> listMap = new LinkedHashMap<List<String>, Object>();

        List<String> list1 = new ArrayList<>();
        list1.add("keyOne1");
        list1.add("keyTwo1");
        List<String> list2 = new ArrayList<>();
        list2.add("keyOne2");
        list2.add("keyTwo2");
        listMap.put(list1, new Auth("1", "2"));
        listMap.put(list2, new Auth("2", "3"));
        listMapBean.setMap(listMap);

        ClassParser parser = new ClassParser();
        ClassBuilder builder = new ClassBuilder();
        try {
            Node node = parser.parse(listMapBean);
            System.out.println(JsonUtils.getJson(node));
            Object build = builder.build(node);
            System.out.println(build == listMapBean);
            System.out.println(JsonUtils.getJson(node));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
