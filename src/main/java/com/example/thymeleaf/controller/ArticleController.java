package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.article.Article;
import com.example.thymeleaf.domain.user.UserDetail;
import com.example.thymeleaf.dto.ArticleRequestDto;
import com.example.thymeleaf.dto.UserRequestDto;
import com.example.thymeleaf.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public String getArticles(@AuthenticationPrincipal UserDetail user, Model model) {

        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "articles/list";
    }

    @GetMapping(value = "/write")
    public String getArticlesWrite() {
        return "articles/write";
    }

    @PostMapping
    public String postArticlesWrite(@ModelAttribute @Valid ArticleRequestDto articleRequestDto) {
        return "articles/list";
    }
}
