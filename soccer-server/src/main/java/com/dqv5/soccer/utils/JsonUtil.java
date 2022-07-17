package com.dqv5.soccer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author duq
 * @date 2022/7/17
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Object toObject(String jsonInString) {
        try {
            return objectMapper.readValue(jsonInString, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
