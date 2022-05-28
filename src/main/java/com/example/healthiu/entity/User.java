package com.example.healthiu.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Column(unique = true)
    private String login;
    @Column
    private String name;

    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private String role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String name, String email, String password, LocalDate dateOfBirth, String role) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }
}