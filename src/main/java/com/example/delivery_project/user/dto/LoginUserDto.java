package com.example.delivery_project.user.dto;

import com.example.delivery_project.user.entity.Authority;
import lombok.Getter;

@Getter
public class LoginUserDto {

    private final Long userId;
    private final Authority authority;

    public LoginUserDto(Long userId, Authority authority) {
        this.userId = userId;
        this.authority = authority;
    }
}
