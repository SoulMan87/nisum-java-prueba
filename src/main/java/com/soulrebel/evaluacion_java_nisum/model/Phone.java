package com.soulrebel.evaluacion_java_nisum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private Long idPhone;

    private String number;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "country_code")
    private Integer countryCode;
}
