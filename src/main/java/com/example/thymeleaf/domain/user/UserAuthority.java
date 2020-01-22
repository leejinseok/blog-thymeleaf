package com.example.thymeleaf.domain.user;

import javax.persistence.*;

@Entity
@Table(name = "authority")
public class UserAuthority {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private RoleType authority;

}
