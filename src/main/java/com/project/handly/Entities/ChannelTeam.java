package com.project.handly.Entities;

import com.project.handly.Enum.ChannelTeamStatus;
import com.project.handly.Enum.EmployeeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "channel_team")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ChannelTeamId.class)
public class ChannelTeam {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    private Channels channel;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ChannelTeamStatus status = ChannelTeamStatus.active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole role = EmployeeRole.reader;

    @Column(name = "expiration")
    private LocalDateTime expiration;


    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
