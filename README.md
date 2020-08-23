# serializer

解决了jackson不能反序列化Map<List,Object>这个种类型的实体变量。

## 使用方式

请使用SerializerUtil进行序列化和反序列化。

``` java

        String serialize = SerializerUtil.serialize(listMapBean);
        Object object=SerializerUtil.deserialize(serialize);

```

## 特新

支持已知的反序列化类型
1.Map类型
2.List类型
3.Java基础类型（Integer,Long,Double,BigDecimal,Date,Boolean）
4.Enum类型
5.普通的Java类型
