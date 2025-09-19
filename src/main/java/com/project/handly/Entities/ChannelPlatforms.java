package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "channel_platforms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPlatforms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private Channels channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", referencedColumnName = "id")
    private Platforms platform;

    @JsonIgnore
    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @JsonIgnore
    @Column(name = "api_secret")
    private String apiSecret;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
