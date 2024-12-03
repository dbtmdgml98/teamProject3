package com.example.delivery_project.user.controller;

import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.CreateUserResponseDto;
import com.example.delivery_project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> creatUser(
        @RequestBody CreateUserRequestDto requestDto
    ) {

        CreateUserResponseDto responseDto = userService.createUser(
            requestDto.getUsername(),
            requestDto.getUserEmail(),
            requestDto.getPassword(),
            requestDto.getAuthority()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
