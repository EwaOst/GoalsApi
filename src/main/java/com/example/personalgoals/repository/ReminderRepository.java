package com.example.personalgoals.repository;

import com.example.personalgoals.model.ReminderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<ReminderModel, Long> {
}
