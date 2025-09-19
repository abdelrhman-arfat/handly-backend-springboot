package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.handly.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "role", nullable = false, columnDefinition = "user_role")
    private Role role = Role.user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;
    @Column(name="phone", nullable = true, unique = true)
    private String phone;

    @Column(name="password")
    @JsonIgnore
    private String password;
    @Column(name="verified_at" ,  nullable = true)
    @JsonProperty("verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonIgnore
    private Country country;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @JsonIgnore
    private List<Channels> channels ;
}
