package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="platforms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Platforms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_ar" ,unique = true,nullable = false)
    @JsonProperty("name_ar")
    private String nameAr;

    @Column(name = "name_en" ,unique = true,nullable = false)
    @JsonProperty("name_en")
    private String nameEn;

    @Column(name= "logo_url" , nullable = false , unique = true)
    @JsonProperty("logo_url")
    private String logoUrl;

    @Column(name = "status" )
    private boolean status;


    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;




}
