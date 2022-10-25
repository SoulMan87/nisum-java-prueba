package com.soulrebel.evaluacion_java_nisum.utils;

import com.soulrebel.evaluacion_java_nisum.model.Password;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

public class PasswordUtils {

    public static String generateEncrypting(String password) {
        if (Objects.isNull(password)) {
            return null;
        }
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean validatedPassword(Password password) {
        var bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(password.getPassword(), password.getEncodedPassword());
    }


}
