package com.example.personalgoals.repository;

import com.example.personalgoals.model.HabitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<HabitModel, Long> {
}
