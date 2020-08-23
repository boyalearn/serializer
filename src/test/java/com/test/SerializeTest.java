package com.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serializer.SerializerUtil;
import com.serializer.exception.SerializeException;
import com.test.entity.Auth;
import com.test.entity.ListMapBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SerializeTest {

    @Test
    public void testSerialize() throws SerializeException {
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
        String serialize = SerializerUtil.serialize(listMapBean);
        System.out.println(serialize);
        Object object=SerializerUtil.deserialize(serialize);
        System.out.println(object.toString());
    }
}
