package com.example.delivery_project.user.entity;

import com.example.delivery_project.common.entity.TimeBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "user")
public class User extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private Authority authority;

    @Enumerated(value = EnumType.ORDINAL)
    private UserWithdraw userWithdraw = UserWithdraw.ACTIVE;

    public User() {
    }

    public User(String username, String userEmail, String encodePassword, String authority) {
        this.name = username;
        this.email = userEmail;
        this.password = encodePassword;
        this.authority = Authority.valueOf(authority);
    }

    public void deleteUser() {
        this.userWithdraw = UserWithdraw.WITHDRAWN;
    }
}
