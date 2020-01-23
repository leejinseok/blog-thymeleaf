package com.example.thymeleaf.domain.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public Article save(Article article) {
        em.persist(article);

        return article;
    }
}
