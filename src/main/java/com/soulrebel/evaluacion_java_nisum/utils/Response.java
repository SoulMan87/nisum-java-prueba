package com.soulrebel.evaluacion_java_nisum.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Response<T> {

    private T message;

}
