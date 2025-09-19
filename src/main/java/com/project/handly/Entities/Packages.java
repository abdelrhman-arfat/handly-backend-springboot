package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "packages")
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name_ar" , nullable = false, unique = true)
    @JsonProperty("name_ar")
    private String nameAr;

    @Column(name="name_en" ,  nullable = false ,unique = true)
    @JsonProperty("name_en")
    private String nameEn;

    @Column(name="price", nullable = false)
    private float price;


    @Column(name = "text_limits", nullable = true)
    @JsonProperty("text_limits")
    private int textLimits;

    @Column(name = "video_limits", nullable = true)
    @JsonProperty("video_limits")
    private int videoLimits;

    @Column(name = "photo_limits", nullable = true)
    @JsonProperty("photo_limits")
    private int photoLimits;

    @Column(name = "benefits",nullable = true)
    private String benefits;


    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
    private List<PackagesSubscription> packagesSubscription;

}

