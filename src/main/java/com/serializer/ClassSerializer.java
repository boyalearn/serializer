package com.serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.serializer.exception.SerializeException;
import com.serializer.node.Node;
import com.serializer.parse.ClassBuilder;
import com.serializer.parse.ClassParser;

import java.lang.reflect.InvocationTargetException;

public class ClassSerializer implements Serializer {
    private ObjectMapper mapper = new ObjectMapper();

    private ClassBuilder classBuilder = new ClassBuilder();

    private ClassParser classParser = new ClassParser();

    public ClassSerializer() {
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
    }

    @Override
    public String serialize(Object object) throws SerializeException {
        try {
            Node node = classParser.parse(object);
            return mapper.writeValueAsString(node);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | JsonProcessingException e) {
            throw new SerializeException(e.getMessage());
        }
    }

    @Override
    public Object deserialize(String context) throws SerializeException {
        try {
            Node node = mapper.readValue(context, Node.class);
            return classBuilder.build(node);

        } catch (JsonProcessingException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new SerializeException(e.getMessage());
        }
    }
}
