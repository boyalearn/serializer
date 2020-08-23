package com.serializer;

import com.serializer.exception.SerializeException;

public class SerializerUtil {

    private static ClassSerializer classSerializer = new ClassSerializer();

    public static String serialize(Object object) throws SerializeException {
        return classSerializer.serialize(object);
    }

    public static Object deserialize(String context) throws SerializeException {
        return classSerializer.deserialize(context);
    }
}
