package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "GOAL")

public class GoalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "GOAL_NAME", length = 128)
    private String goalName;

    @Column(name = "GOAL_CATEGORY", length = 128)
    private String goalCategory;

    @Column(name = "START_DATE")
    private ZonedDateTime startDate;

    @Column(name = "END_DATE")
    private ZonedDateTime endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;


}


//Identyfikator celu
//Nazwa celu
//Kategoria celu (np. zdrowie, nauka, finanse)
//Data początkowa i końcowa celu
//Identyfikator użytkownika powiązany z celem