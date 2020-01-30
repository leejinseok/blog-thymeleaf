package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.article.Article;
import com.example.thymeleaf.domain.article.ArticleRepository;
import com.example.thymeleaf.domain.user.User;
import com.example.thymeleaf.dto.ArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(ArticleRequestDto requestDto, User user) {
        return articleRepository.save(new Article(requestDto, user));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id);
    }
}
