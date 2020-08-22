package com.test.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.serializer.JsonSerialize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class JacksonTest {
    public static void main(String[] args) throws JsonProcessingException {

        JsonSerialize jsonSerialize=new JsonSerialize();

        MapBean mapBean = new MapBean("");

        ListMapBean listMapBean=new ListMapBean();
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        LinkedHashMap<List<String>, Object> listMap = new LinkedHashMap<List<String>, Object>();

        List<String> list1 = new ArrayList<>();
        list1.add("keyOne1");
        list1.add("keyTwo1");
        List<String> list2 = new ArrayList<>();
        list2.add("keyOne2");
        list2.add("keyTwo2");
        map.put("key1", new Auth("one", "two"));
        map.put("key2", new Auth("one", "two"));
        listMap.put(list1,new Auth("1","2"));
        listMap.put(list2,new Auth("2","3"));
        listMapBean.setMap(listMap);
        mapBean.setMap(map);
        String s = jsonSerialize.serialize(mapBean);
        System.out.println(s);

        MapBean resultMap = (MapBean) jsonSerialize.deserialize(s);
        System.out.println(resultMap);

        String listString = jsonSerialize.serialize(listMapBean);
        System.out.println(listString);

        ListMapBean resultListMap = (ListMapBean) jsonSerialize.deserialize(listString);
        System.out.println(resultListMap);


    }
}
