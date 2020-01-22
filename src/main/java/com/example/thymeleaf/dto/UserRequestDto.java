package com.example.thymeleaf.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {

    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    private String password;

}
