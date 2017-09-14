package com.jackshenorion.smarts.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    public static <T> T readFromFile(String file, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(file), tClass);
    }

    public static <T> String writeAsJsonString(T t) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
    }

}
