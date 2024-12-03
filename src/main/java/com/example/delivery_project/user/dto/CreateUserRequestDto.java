package com.example.delivery_project.user.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private final String userName;
    private final String userEmail;
    private final String password;
    private final String authority;

    public CreateUserRequestDto(String userName, String userEmail, String password,
        String authority) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.password = password;
        this.authority = authority;
    }
}
