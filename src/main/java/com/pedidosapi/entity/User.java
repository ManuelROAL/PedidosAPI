package com.pedidosapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "user", indexes = {
        @Index(name = "email_UNIQUE", columnList = "email", unique = true),
        @Index(name = "alias_UNIQUE", columnList = "username", unique = true)
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "lastname", length = 60)
    private String lastname;
}