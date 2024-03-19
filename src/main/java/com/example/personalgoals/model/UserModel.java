package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_DATA")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "USER", columnDefinition = "TEXT", nullable = false)
//    private String users;

    @Column(name = "USER_NAME", length = 128, nullable = false)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AGE")
    private String age;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "GENDER")
//    private GenderEnum gender;


}
