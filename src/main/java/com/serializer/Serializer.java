package com.serializer;

import com.serializer.exception.SerializeException;

public interface Serializer {

    String serialize(Object object) throws SerializeException;

    Object deserialize(String context) throws SerializeException;
}
