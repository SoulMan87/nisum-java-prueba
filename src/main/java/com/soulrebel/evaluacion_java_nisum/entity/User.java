package com.soulrebel.evaluacion_java_nisum.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "uuid", updatable = false, name = "id_user")
    private UUID idUser;

    @NotEmpty(message = "Field 'Name' is required.")
    private String name;

    @NotEmpty(message = "Field 'Email' is required.")
    @Email(message = "Field 'Email' must have a correct format.")
    private String email;

    @Pattern(regexp = "[0-9]{2}[A-Z][a-z]*", message = "Field 'Password' must have a correct format.")
    @NotEmpty(message = "Field 'Password' is required.")
    private String password;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "last_login")
    private LocalDateTime last_login;

    @Column(name = "token")
    private String token;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private List<Phone> phones;

    @Column(name = "is_active")
    private Integer isActive;

    public static boolean checkUserEmail(String email) {
        var EMAIL_PATTERN = String.format("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        return email.matches(EMAIL_PATTERN);
    }

}
