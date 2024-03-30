package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "HABIT")
public class HabitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HABIT_NAME", length = 128)
    private String habitName;

    @Column(name = "HABIT_FREQUENCY", length = 128)
    private Frequency frequency;

    @Column(name = "TRACK")
    private boolean track;

    @Column(name = "ACTIVITY")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userId;

}
