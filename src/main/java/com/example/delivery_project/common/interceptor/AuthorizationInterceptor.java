package com.example.delivery_project.common.interceptor;

import com.example.delivery_project.user.entity.Authority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws ResponseStatusException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "세션이 만료되었습니다.");
        }

        Authority authority = (Authority) session.getAttribute("userAuthority");

        if (authority != Authority.OWNER) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "OWNER 만 접근할 수 있습니다");
        }

        return true;
    }
}
