package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "GOAL")

public class GoalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GOAL", columnDefinition = "TEXT", nullable = false)
    private String goal;

    @Column(name = "GOAL_NAME", length = 128)
    private String goalName;

    @Column(name = "GOAL_CATEGORY")
    private Category category;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userId;


    }








//Identyfikator celu
//Nazwa celu
//Kategoria celu (np. zdrowie, nauka, finanse)
//Data początkowa i końcowa celu
//Identyfikator użytkownika powiązany z celem