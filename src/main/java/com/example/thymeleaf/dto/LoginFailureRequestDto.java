package com.example.thymeleaf.dto;

import lombok.Data;

@Data
public class LoginFailureRequestDto {
    String username;
    String password;
    String exception;
}
