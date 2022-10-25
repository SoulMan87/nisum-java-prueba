package com.soulrebel.evaluacion_java_nisum.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Password {

    private String password;

    private String encodedPassword;
}
