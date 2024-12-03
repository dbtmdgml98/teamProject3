package com.example.delivery_project.user.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private final String username;
    private final String userEmail;
    private final String password;
    private final String authority;

    public CreateUserRequestDto(String username, String userEmail, String password,
        String authority) {
        this.username = username;
        this.userEmail = userEmail;
        this.password = password;
        this.authority = authority;
    }
}
