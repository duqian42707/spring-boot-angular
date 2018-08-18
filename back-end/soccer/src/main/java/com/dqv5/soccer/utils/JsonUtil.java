package com.dqv5.soccer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author duq
 * @date 2018/5/31
 */
public class JsonUtil {

    public static Object toObject(String jsonInString){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object obj = mapper.readValue(jsonInString, Object.class);
            return obj;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
