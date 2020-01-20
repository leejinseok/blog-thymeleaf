package com.example.thymeleaf.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterForm {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
