package com.soulrebel.evaluacion_java_nisum.service;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import com.soulrebel.evaluacion_java_nisum.model.UserResponse;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User findByEmail(String email);

    Optional<UserResponse> saveUser(User user);

    Optional<User> findByUUID(UUID id);

    Optional<User> validateUserToken(String token);

}
