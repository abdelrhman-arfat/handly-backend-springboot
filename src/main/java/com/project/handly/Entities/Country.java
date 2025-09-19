package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_ar" , nullable = false, unique = true)
    @JsonProperty("name_ar")
    private String nameAr;

    @Column(name="name_en" ,  nullable = false ,unique = true)
    @JsonProperty("name_en")
    private String nameEn;

    @Column(name="code", nullable = false)
    private String code;

    @Column(name="phone_code" ,nullable=false)
    @JsonProperty("phone_code")
    private String phoneCode;

    @Column(name = "currency" , nullable = false)
    private String currency;
<<<<<<< HEAD


    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "country", cascade = CascadeType.PERSIST)
    private List<User> users ;
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
}
