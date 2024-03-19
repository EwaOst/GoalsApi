package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "USER_DATA")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "USERS", columnDefinition = "TEXT")
//    private String users;

    @Column(name = "USER_NAME", length = 128, nullable = false)
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AGE")
    private String age;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GoalModel> goals = new HashSet<>();

//    @Enumerated(EnumType.STRING)
//    @Column(name = "GENDER")
//    private GenderEnum gender;


}
