package com.example.delivery_project.common.config;

import com.example.delivery_project.common.interceptor.AuthenticationInterceptor;
import com.example.delivery_project.common.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//인증, 인가 수행할 때 사용할 Config 파일
//일단 프로젝트 과정 중 편의를 위해 인증 인가 안걸리게 주석처리 해둠
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    //인증 포함 경로
    private static final String[] AUTHENTICATION_REQUIRED_PATHS = {
        "/api/users/**", "/api/stores/**"
    };

    //인증 제외 경로
    private static final String[] AUTHENTICATION_EXCLUDED_PATHS = {
        "/api/users/login", "/api/users/signup"
    };

    //인가 포함 경로
    private static final String[] AUTHORIZATION_REQUIRED_PATHS = {
        "/api/owners/**", // 모든 사장 관련 경로
    };


    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns(AUTHENTICATION_REQUIRED_PATHS)
            .excludePathPatterns(AUTHENTICATION_EXCLUDED_PATHS)
            .order(Ordered.HIGHEST_PRECEDENCE);

        registry.addInterceptor(authorizationInterceptor)
            .addPathPatterns(AUTHORIZATION_REQUIRED_PATHS)
            .order(Ordered.HIGHEST_PRECEDENCE + 1);
    }
}
