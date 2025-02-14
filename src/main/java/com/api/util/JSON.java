package com.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSON {

    private static final Logger logger = LoggerFactory.getLogger(JSON.class);
    private static final ObjectMapper om = new ObjectMapper();

    static {
        om.registerModule(new JavaTimeModule());
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String stringify(Object ob){
        try {
            return om.writeValueAsString(ob);
        } catch (JsonProcessingException e) {
            logger.error("Error converting object to JSON: ", e);
            return null;
        }
    }

    public static <T> T parse(String json, Class<T> tClass){
        try {
            return om.readValue(json, tClass);
        } catch (JsonMappingException e) {
            logger.error(String.format("Error mapping the JSON: <%s>", json), e);
            return null;
        } catch (JsonProcessingException e) {
            logger.error(String.format("Error processing the JSON: <%s>", json), e);
            return null;
        }
    }
}
