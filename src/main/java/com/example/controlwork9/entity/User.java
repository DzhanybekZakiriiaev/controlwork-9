package com.example.controlwork9.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", length = 25, nullable = false)
    private String email;

    @Column(name = "password", length = 16, nullable = false)
    private String password;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

}
