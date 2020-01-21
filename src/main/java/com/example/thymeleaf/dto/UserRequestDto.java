package com.example.thymeleaf.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
public class UserRequestDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

}
