package com.example.delivery_project.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    private final String password;

    public DeleteUserRequestDto(@JsonProperty("password") String password) {
        this.password = password;
    }
}
