package com.example.personalgoals.model;

import jakarta.persistence.*;

public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", length = 128)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AGE")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private GenderEnum gender;


}


//Nazwa użytkownika
//Hasło (zahaszowane)
//Dane osobowe użytkownika (np. adres email, wiek, płeć)