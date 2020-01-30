package com.example.thymeleaf.domain.article;

import com.example.thymeleaf.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    public Article save(Article article) {
        em.persist(article);

        return article;
    }

    public List<Article> findAll() {
        String jpql = "select a from Article a ";
        TypedQuery<Article> query = em.createQuery(jpql, Article.class);
        em.close();

        return query.getResultList();
    }

    public Article findById(Long id) {
        return em.find(Article.class, id);
    }
}
