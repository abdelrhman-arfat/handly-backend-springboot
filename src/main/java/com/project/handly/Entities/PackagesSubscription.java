package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "packages_subscription")
public class PackagesSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",nullable = false)
    private Channels channel;


    @Column(name = "photo_used" )
    @JsonProperty("photo_used")
    private int photoUsed = 0;

    @Column(name = "video_used" )
    @JsonProperty("video_used")
    private int videoUsed = 0;

    @Column(name = "text_used" )
    @JsonProperty("text_used")
    private int textUsed = 0;

    @Column(name = "start_date" , nullable = false)
    @JsonProperty("start_date")
    private Date startDate;


    @Column(name = "end_date" , nullable = false)
    @JsonProperty("end_date")
    private Date endDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Packages pkg;
}
