package cn.dlbdata.dj.common.util;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FastJsonConverter {
    public static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    public static <T> String toJSONStr(T param) throws IOException {
        return objectMapper.writeValueAsString(param);
    }

    public static <T> T toObject(String param, Class<T> objType) throws IOException {
        return objectMapper.readValue(param, objType);
    }

}
