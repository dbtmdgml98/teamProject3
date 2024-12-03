package com.example.delivery_project.user.service;

import com.example.delivery_project.common.config.PasswordEncoder;
import com.example.delivery_project.common.util.UtilValidation;
import com.example.delivery_project.user.dto.CreateUserRequestDto;
import com.example.delivery_project.user.dto.CreateUserResponseDto;
import com.example.delivery_project.user.dto.LoginRequestDto;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        if (!UtilValidation.isValidPasswordFormat(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "비밀번호는 최소 8자, 대소문자 포함한 영문, 숫자, 특수문자를 포함해야합니다.");
        }

        User user = new User(
            requestDto.getUserName(),
            requestDto.getUserEmail(),
            passwordEncoder.encode(requestDto.getPassword()),
            requestDto.getAuthority()
        );

        User userByEmail = userRepository.findByEmail(requestDto.getUserEmail());

        if (userByEmail != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "중복된 이메일 입니다.");
        }

        User savedUser = userRepository.save(user);

        return new CreateUserResponseDto(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getCreatedAt()
        );
    }

    public void login(HttpServletRequest request, LoginRequestDto requestDto) {
        HttpSession session = request.getSession(true);

        User findUser = userRepository.findByEmail(requestDto.getUserEmail());

        if (findUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저 이메일 입니다");
        }

        if (!passwordEncoder.matches(requestDto.getPassword(), findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다");
        }

        session.setAttribute("userId", findUser.getId());
        session.setAttribute("userAuthority", findUser.getAuthority());
    }

}
