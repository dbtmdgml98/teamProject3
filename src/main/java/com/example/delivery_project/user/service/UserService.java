package com.example.delivery_project.user.service;

import com.example.delivery_project.common.config.PasswordEncoder;
import com.example.delivery_project.common.util.UtilValidation;
import com.example.delivery_project.user.dto.CreateUserResponseDto;
import com.example.delivery_project.user.entity.User;
import com.example.delivery_project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserResponseDto createUser(
        String username,
        String userEmail,
        String password,
        String authority
    ) {
        if (!UtilValidation.isValidPasswordFormat(password)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "비밀번호는 최소 8자, 대소문자 포함한 영문, 숫자, 특수문자를 포함해야합니다.");
        }

        User user = new User(username, userEmail, passwordEncoder.encode(password), authority);

        User userByEmail = userRepository.findByEmail(userEmail);

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
}
