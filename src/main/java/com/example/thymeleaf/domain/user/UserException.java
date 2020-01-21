package com.example.thymeleaf.domain.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class AlreadyExist extends RuntimeException {
        public AlreadyExist(String username) {
            super("이미 존재하는 사용자입니다. [username=" + username + "]");
        }
    }
}
