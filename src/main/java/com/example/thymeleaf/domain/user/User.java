package com.example.thymeleaf.domain.user;

import com.example.thymeleaf.domain.article.Article;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
