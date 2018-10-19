package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jnj.adf.grid.data.temporal.TemporalRKey;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

public final class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static final <T> T jsonToObject(byte[] bytes, Class<T> classType) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Object value = null;

        try {
            value = mapper.readValue(bytes, classType);
        } catch (IOException var4) {
            LogUtil.getCoreLog().warn("Json bytes convert to class {} failed", new Object[]{classType.getName(), var4});
        }

        return value;
    }

    public static final <T> T jsonToObject(String jsonString, Class<T> classType) {
        Object value = null;

        try {
            if (!StringUtils.isEmpty(jsonString)) {
                value = mapper.readValue(jsonString, classType);
            }
        } catch (IOException var4) {
            LogUtil.getCoreLog().warn("Json string {} convert to class {} failed", new Object[]{jsonString, classType.getName(), var4});
        }

        return value;
    }

    public static final String objectToJson(Object value) {
        if (value instanceof String) {
            return (String)value;
        } else {
            String str = null;

            try {
                str = mapper.writeValueAsString(value);
            } catch (IOException var3) {
                LogUtil.getCoreLog().warn("Object convert to string failed", var3);
            }

            return str;
        }
    }

    public static final byte[] objectToBytes(Object value) {
        byte[] dataBytes = null;

        try {
            dataBytes = mapper.writeValueAsBytes(value);
        } catch (IOException var3) {
            LogUtil.getCoreLog().warn("Object convert to byte[] failed", var3);
        }

        return dataBytes;
    }

    public static final <T> T convertValue(Object value, Class<T> toClass) {
        try {
            T resutl = null;
            if (value != null) {
                resutl = mapper.convertValue(value, toClass);
            }

            return resutl;
        } catch (Exception var3) {
            LogUtil.getCoreLog().warn("Object convert to {} failed", new Object[]{toClass.getSimpleName(), var3});
            return null;
        }
    }

    public static void main(String[] args) {
        String address = "192.168.120.82[12244]";
        byte[] aaa = objectToBytes(address);
        System.out.println((String)jsonToObject(aaa, String.class));
        System.out.println(jsonToObject("{\"uuid\":\"9ad6a506-fa62-42af-96c2-9e0826f0f95f\",\"identityKey\":\"k1\",\"name\":\"com.jnj.adf.grid.data.temporal.TemporalRKey\"}", TemporalRKey.class));
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
