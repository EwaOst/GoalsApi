package com.example.personalgoals.repository;

import com.example.personalgoals.model.GoalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<GoalModel, Long> {
}
