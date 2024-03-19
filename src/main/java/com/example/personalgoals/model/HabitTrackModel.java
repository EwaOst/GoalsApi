package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TRACKING")
public class HabitTrackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
