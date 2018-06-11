package cn.wolfcode.wms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public JSONUtil() {
    }

    public static String toJsonString(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
