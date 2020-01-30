package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.UserRequestDto;
import com.example.thymeleaf.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/register")
    public String getRegister() {
        return "auth/register";
    }

    @PostMapping("/register")
    @Transactional
    public String postRegister(@ModelAttribute @Valid UserRequestDto userRequestDto) {
        authService.saveUser(userRequestDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/auth/login";
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpServletRequest request) {
        model.addAttribute("exception", request.getAttribute("exception"));
        return "auth/login";
    }

}
