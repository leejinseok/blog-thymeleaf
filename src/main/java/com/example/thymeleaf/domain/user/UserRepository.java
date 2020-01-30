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
        em.close();
        return user;
    }

    public List<User> findByUsername(String name) {
        String jpql = "select u from User u where u.name = :username";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("username", name);
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
