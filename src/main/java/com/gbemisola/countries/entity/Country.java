package com.gbemisola.countries.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "COUNTRIES")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code")
    private String countryCode;

}