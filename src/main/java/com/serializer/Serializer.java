package com.serializer;

public interface Serializer {

    String serialize(Object object);

    Object deserialize(String context);
}
