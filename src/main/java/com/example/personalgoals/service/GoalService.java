package com.example.personalgoals.service;

import com.example.personalgoals.model.Category;
import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalModel createGoal(GoalModel goal) {
        GoalModel newGoal = new GoalModel();
        newGoal = goalRepository.save(newGoal);
        return newGoal;
    }


    public Optional<GoalModel> findGoalById(Long id) {
        Optional<GoalModel> goal = goalRepository.findById(id);
        if (goal.isPresent()) {
            return goal; // Zwróć cel, jeśli istnieje
        } else {
            throw new EntityNotFoundException("Goal not found");
        }
    }

    public Optional<GoalModel> updateGoal(Long id, GoalModel updateGoal) {
        return Optional.ofNullable(goalRepository.findById(id)
                .map(goal -> {
                    goal.setGoalName(updateGoal.getGoalName());
                    goal.setCategory(updateGoal.getCategory());
                    goal.setStartDate(updateGoal.getStartDate());
                    goal.setEndDate(updateGoal.getEndDate());
                    return goalRepository.save(goal);
                })
                .orElseThrow(() -> new RuntimeException("Goal do not exists")));
    }

    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        goalRepository.deleteById(id);
    }

}

