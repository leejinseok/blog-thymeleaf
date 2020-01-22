package com.example.thymeleaf.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException {

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class AlreadyExistNickname extends RuntimeException {
        public AlreadyExistNickname(String username) {
            super("이미 존재하는 닉네임입니다. [username=" + username + "]");
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class AlreadyExistEmail extends RuntimeException {
        public AlreadyExistEmail(String email) {
            super("이미 존재하는 닉네임입니다. [email=" + email + "]");
        }
    }
}
