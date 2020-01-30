package com.example.thymeleaf.controller;

import com.example.thymeleaf.domain.article.Article;
import com.example.thymeleaf.domain.user.UserDetail;
import com.example.thymeleaf.dto.ArticleRequestDto;
import com.example.thymeleaf.dto.UserRequestDto;
import com.example.thymeleaf.service.ArticleService;
import com.example.thymeleaf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;

    @GetMapping
    public String getArticles(@AuthenticationPrincipal UserDetail user, Model model) {

        List<Article> articles = articleService.findAll();
        model.addAttribute("articles", articles);

        return "articles/list";
    }

    @GetMapping(value = "/{id}")
    public String getArticle(@AuthenticationPrincipal UserDetail user, @PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.findById(id));
        return "articles/detail";
    }

    @GetMapping(value = "/write")
    public String getArticlesWrite() {
        return "articles/write";
    }

    @PostMapping
    @Transactional
    public String postArticlesWrite(@AuthenticationPrincipal UserDetail userDetail, @ModelAttribute @Valid ArticleRequestDto articleRequestDto) {
        articleService.save(articleRequestDto, userService.findUser(userDetail));
        return "redirect:/articles";
    }
}
