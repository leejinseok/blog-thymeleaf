package com.example.thymeleaf.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class PasswordNotMatchException extends AuthenticationException {
        public PasswordNotMatchException() {
            super("비밀번호가 일치하지 않습니다.");
        }
    }

}
