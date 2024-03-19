package com.example.personalgoals.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
@Entity
@Data
@Table(name = "REMINDER")
public class ReminderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REMINDER_CONTENT", length = 128)
    private String reminderContent;

    @Column(name = "REMINDER_DATE")
    private ZonedDateTime reminderDate;

    @Column(name = "REMINDER_TIME")
    private ZonedDateTime reminderTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;



}
