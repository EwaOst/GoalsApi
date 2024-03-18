package com.example.personalgoals.model;

import jakarta.persistence.*;

public class HabitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HABIT_NAME", length = 128)
    private String habitName;

    @Column(name = "HABIT_FREQUENCY", length = 128)
    private String habitFrequency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;






//    Identyfikator nawyku
//    Nazwa nawyku
//    Częstotliwość nawyku (np. codziennie, tygodniowo, miesięcznie)
//    Aktywność związana z nawykiem (np. data wykonania, liczba powtórzeń)
//    Identyfikator użytkownika powiązany z nawykiem
}
