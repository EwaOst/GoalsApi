package com.example.personalgoals.repository;

import com.example.personalgoals.model.StatisticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<StatisticsModel, Long> {
}
