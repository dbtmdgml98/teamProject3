package com.example.delivery_project.user.controller;

import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.CreateUserResponseDto;
import com.example.delivery_project.user.dto.DeleteUserRequestDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import com.example.delivery_project.user.dto.LoginUserDto;
import com.example.delivery_project.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class
UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> creatUser(
        @RequestBody CreateUserRequestDto requestDto
    ) {

        CreateUserResponseDto responseDto = userService.createUser(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
        HttpServletRequest request,
        @RequestBody LoginRequestDto requestDto
    ) {

        HttpSession session = request.getSession(true);
        LoginUserDto dto = userService.login(requestDto);

        session.setAttribute("userId", dto.getUserId());
        session.setAttribute("userAuthority", dto.getAuthority());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
        HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Void> deleteUser(
        HttpServletRequest request,
        @RequestBody DeleteUserRequestDto requestDto
    ) {
        /*
        로그인 인증 과정이 이미 인터셉터로 처리되고 있기에
        따로 컨트롤러 단에서의 검증은 필요가 없다.
        */
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        userService.deleteUser(userId, requestDto);

        return null;
    }
}
