package com.soulrebel.evaluacion_java_nisum.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class MockUtils {

    public static <T> T getMock(String path, Class<T> clazz) throws IOException {
        var objectMapper = new ObjectMapper();
        var classPathResource = new ClassPathResource(path);
        return objectMapper.readValue(classPathResource.getInputStream(), clazz);
    }
}
