package com.soulrebel.evaluacion_java_nisum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @NotEmpty(message = "Field 'Name' is required.")
    private String name;

    @NotEmpty(message = "Field 'Email' is required.")
    @Email(message = "Field 'Email' must have a correct format.")
    private String email;

    @Pattern(regexp = "[0-9]{2}[A-Z][a-z]*", message = "Field 'Password' must have a correct format.")
    @NotEmpty(message = "Field 'Password' is required.")
    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private List<Phone> phones;

    @Temporal(TemporalType.DATE)
    private Date created;

    @Temporal(TemporalType.DATE)
    private Date modified;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_login")
    private Date lastLogin;

    private String token;

    @Column(name = "is_active")
    private Integer isActive;

}
