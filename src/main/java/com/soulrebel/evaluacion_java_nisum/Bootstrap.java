package com.soulrebel.evaluacion_java_nisum;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Bootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String... args) {
        LOGGER.info("Starting...");
        SpringApplication.run(Bootstrap.class, args);
    }
}
