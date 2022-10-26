package com.soulrebel.evaluacion_java_nisum.controller;

import com.soulrebel.evaluacion_java_nisum.entity.User;
import com.soulrebel.evaluacion_java_nisum.model.UserResponse;
import com.soulrebel.evaluacion_java_nisum.service.UserService;
import com.soulrebel.evaluacion_java_nisum.utils.PasswordUtils;
import com.soulrebel.evaluacion_java_nisum.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.soulrebel.evaluacion_java_nisum.utils.Constants.DURATION;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.EMAIL_ALREADY_EXIST;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.EMAIL_MESSAGE;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.INVALID_SESSION;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.INVALID_TOKE_OR_ID;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.INVALID_USER;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.MESSAGE;
import static com.soulrebel.evaluacion_java_nisum.utils.Constants.NOT_AUTHORIZED;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IUserController implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity addUser(User user) {

        var response = new Response<>();

        if (user.getName().isEmpty() || user.getPassword().isEmpty()) {
            response.setMessage(MESSAGE);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
        if (!User.checkUserEmail(user.getEmail())) {
            response.setMessage(EMAIL_MESSAGE);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }
        if (service.findByEmail(user.getEmail()) != null) {
            response.setMessage(EMAIL_ALREADY_EXIST);
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(user));
    }

    @Override
    public ResponseEntity userAccess(User user) {

        var userByEmail = service.findByEmail(user.getEmail());

        var response = new Response<>();
        if (Objects.nonNull(userByEmail) && (PasswordUtils.validatedPassword(user.getPassword(),
                userByEmail.getPassword()))) {

            var userResponse = new UserResponse(userByEmail.getId(), userByEmail.getCreated()
                    , userByEmail.getModified(), userByEmail.getLast_login(), userByEmail.getToken(), userByEmail.isActive());
            return ResponseEntity.ok().body(userResponse);
        } else {
            response.setMessage(INVALID_USER);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }

    @Override
    public ResponseEntity userToken(String token, String id) {

        var response = new Response<>();

        if (token.isEmpty() || id.isEmpty()) {
            response.setMessage(INVALID_TOKE_OR_ID);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        var userToken = service.validateUserToken(token);
        if (Objects.isNull(userToken)) {
            response.setMessage(NOT_AUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        var uuid = UUID.fromString(id);
        var userUUID = service.findByUUID(uuid);

        if (!userUUID.isPresent()) {
            response.setMessage(NOT_AUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        if (userUUID.get().getToken().equals(token)) {
            LocalDateTime dataNow = LocalDateTime.now();
            LocalDateTime lastLogin = userUUID.get().getLast_login();
            Duration duration = Duration.between(dataNow, lastLogin);
            if (duration.toMinutes() > DURATION) {
                response.setMessage(INVALID_SESSION);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } else {
                return ResponseEntity.ok().body(buildUserResponseUUID(uuid));
            }
        } else {
            response.setMessage(NOT_AUTHORIZED);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    private UserResponse buildUserResponseUUID(UUID uuid) {
        var userUUid = service.findByUUID(uuid);
        return UserResponse.builder()
                .id(userUUid.get().getId())
                .created(userUUid.get().getCreated())
                .modified(userUUid.get().getModified())
                .last_login(userUUid.get().getLast_login())
                .token(userUUid.get().getToken())
                .build();
    }

}
