package com.example.personalgoals.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class StatisticsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}


//Statystyki dotyczące realizacji celów i nawyków (np. postępy, osiągnięcia)