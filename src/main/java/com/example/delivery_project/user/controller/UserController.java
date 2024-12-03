package com.example.delivery_project.user.controller;

import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.CreateUserResponseDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import com.example.delivery_project.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
            requestDto.getUserName(),
            requestDto.getUserEmail(),
            requestDto.getPassword(),
            requestDto.getAuthority()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
        HttpServletRequest request,
        @RequestBody LoginRequestDto requestDto
    ) {
        userService.login(request, requestDto.getUserEmail(), requestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
