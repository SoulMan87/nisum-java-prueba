package com.soulrebel.evaluacion_java_nisum.controller;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
public interface UserController {

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity addUser(@RequestBody User user);

    @PostMapping(value = "/access", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity userAccess(@RequestBody User user);

    @PostMapping(value = "/token", consumes = APPLICATION_JSON_VALUE)
    ResponseEntity userToken(@RequestHeader(value = "token", required = false) String token, @RequestParam String id);
}
