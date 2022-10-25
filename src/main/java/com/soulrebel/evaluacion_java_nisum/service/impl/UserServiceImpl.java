package com.soulrebel.evaluacion_java_nisum.service.impl;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import com.soulrebel.evaluacion_java_nisum.model.UserResponse;
import com.soulrebel.evaluacion_java_nisum.repository.UserRepository;
import com.soulrebel.evaluacion_java_nisum.service.UserService;
import com.soulrebel.evaluacion_java_nisum.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public User findUserByEmail(String email) {
        return repository.findByUserEmail(email);
    }

    @Override
    public Optional<UserResponse> saveUser(User user) {

        return Optional.of(buildUserResponse(user));
    }

    private User createUserToken(User user) {
        var password = PasswordUtils.generateEncrypting(user.getIdUser().toString());

        return repository.save(User.builder().token(password).build());
    }

    @Override
    public Optional<User> validateUserToken(String token) {
        return Optional.of(repository.findByToken(token));
    }

    @Override
    public Optional<User> findByUUID(UUID id) {
        return Optional.of(repository.findByUserId(id));
    }

    private User buildUser(User user) {
        var encodedPassword = PasswordUtils.generateEncrypting(user.getPassword());
        return User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(encodedPassword)
                .phones(user.getPhones())
                .build();
    }

    private UserResponse buildUserResponse(User user) {
        var userSaved = repository.save(buildUser(user));
        var userUpdated = createUserToken(userSaved);
        return UserResponse.builder()
                .id(userUpdated.getIdUser())
                .created(userUpdated.getCreated())
                .last_login(userUpdated.getLast_login())
                .token(userUpdated.getToken())
                .build();
    }

}
