package com.example.delivery_project.user.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CreateUserResponseDto {

    private final Long userId;
    private final String userName;
    private final String userEmail;
    private final LocalDateTime createdAt;

    public CreateUserResponseDto(Long userId, String userName, String userEmail,
        LocalDateTime createdAt) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }
}
