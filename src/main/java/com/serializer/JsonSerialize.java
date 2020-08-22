package com.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serializer.parser.Node;
import com.serializer.parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class JsonSerialize implements Serializer {

    private ObjectMapper mapper=new ObjectMapper();

    private Parser parser=new Parser();

    @Override
    public String serialize(Object object) {
        Node node;
        try {
            node=parser.parse(object);
            return mapper.writeValueAsString(node);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object deserialize(String context) {
        try {
            Node node = mapper.readValue(context, Node.class);
            return parser.build(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
