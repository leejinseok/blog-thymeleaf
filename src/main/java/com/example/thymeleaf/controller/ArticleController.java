package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.user.UserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping
    public String getArticles(@AuthenticationPrincipal UserDetail user, Model model) {
        model.addAttribute("test", "test");
        return "articles";
    }
}
