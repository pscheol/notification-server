package com.devpaik.nfs.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Serializer {
    private final ObjectMapper objectMapper;

    public Serializer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public <V> String serialize(V event) {
        try {
            String result = objectMapper.writeValueAsString(event);
            log.debug("serialize..={}", result);
            return result;
        } catch (JsonProcessingException e) {
            throw new PayloadConvertException(e);
        }
    }
    public <T> T deserialize(String src, Class<T> clz) {
        try {
            return objectMapper.readValue(src, clz);
        } catch (Exception e) {
            throw new PayloadConvertException(e);
        }
    }
}
