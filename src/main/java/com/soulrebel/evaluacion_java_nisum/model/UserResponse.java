package com.soulrebel.evaluacion_java_nisum.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class UserResponse {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime last_login;
    private String token;

    private boolean active;

    public UserResponse(
            final UUID id,
            final LocalDateTime created,
            final LocalDateTime modified,
            final LocalDateTime last_login,
            final String token,
            final boolean active) {
        this.id = id;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.token = token;
        this.active = active;
    }
}
