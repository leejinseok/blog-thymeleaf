package com.example.thymeleaf.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public List<User> findByUsername(String username) {
        String jpql = "select u from User u where u.username = :username";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("username", username);

        return query.getResultList();
    }

}
