package com.soulrebel.evaluacion_java_nisum.service.impl;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import com.soulrebel.evaluacion_java_nisum.model.UserResponse;
import com.soulrebel.evaluacion_java_nisum.repository.UserRepository;
import com.soulrebel.evaluacion_java_nisum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.soulrebel.evaluacion_java_nisum.utils.PasswordUtils.generateEncrypting;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<UserResponse> saveUser(User user) {
        var encodedPass = generateEncrypting(user.getPassword());
        var userBuild = new User(user.getName(), user.getEmail(), encodedPass, user.getPhones());
        var userRepo = repository.save(userBuild);
        var userToken = createUserToken(userRepo);
        var response = new UserResponse(userToken.getId(), userToken.getCreated(), userToken.getModified(),
                userToken.getLast_login(), userToken.getToken(), userToken.isActive());

        return Optional.of(response);
    }

    private User createUserToken(User user) {
        var password = generateEncrypting(user.getId().toString());
        user.setToken(password);
        return repository.save(user);
    }

    @Override
    public Optional<User> validateUserToken(String token) {
        return Optional.of(repository.findByToken(token));
    }

    @Override
    public Optional<User> findByUUID(UUID id) {
        return Optional.of(repository.findById(id));
    }

}
