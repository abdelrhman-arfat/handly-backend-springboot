package com.project.handly.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.handly.Enum.Role;
import jakarta.persistence.*;
=======
import com.project.handly.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
=======
import org.hibernate.annotations.Type;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

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
<<<<<<< HEAD
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "role", nullable = false, columnDefinition = "user_role")
    private Role role = Role.user;
=======
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;
<<<<<<< HEAD
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
//    @JsonProperty("country")
    @JsonIgnore
    private Country country;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @JsonIgnore
    private List<Channels> channels ;
=======

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name="phone", nullable = false, unique = true)
    private String phone;

    @Column(name="country_id")
    private Long countryId;

>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
}
