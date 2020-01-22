package com.example.thymeleaf.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

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
        em.close();

        return query.getResultList();
    }

    public List<User> findByEmail(String email) {
        String jpql = "select u from User u where u.email = :email";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("email", email);

        em.close();
        return query.getResultList();
    }

}
