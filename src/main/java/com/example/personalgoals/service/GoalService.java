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
            goal.get();
        } else {
            throw new EntityNotFoundException("Goal not found");
        }
        return goal;
    }

    public GoalModel updateGoal(Long id, GoalModel updateGoal) {
        return goalRepository.findById(id)
                .map(goal -> {
                    goal.setGoalName(updateGoal.getGoalName());
                    goal.setCategory(updateGoal.getCategory());
                    goal.setStartDate(updateGoal.getStartDate());
                    goal.setEndDate(updateGoal.getEndDate());
                    return goalRepository.save(goal);
                })
                .orElseThrow(() -> new RuntimeException("Goal do not exists"));
    }

    public void deleteGoal(Long id) {
        if (!goalRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        goalRepository.deleteById(id);
    }


//    public GoalModel updateGoalCategory(Long goalId, String category) {
//        GoalModel goal = goalRepository.findById(goalId)
//                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
//        goal.setCategory(Category.valueOf(category));
//        return goalRepository.save(goal);
//    }
//
//    public GoalModel updateGoalDates(Long goalId, LocalDate startDate, LocalDate endDate) {
//        GoalModel goal = goalRepository.findById(goalId)
//                .orElseThrow(() -> new EntityNotFoundException("Goal not found"));
//        goal.setStartDate(ZonedDateTime.from(startDate));
//        goal.setEndDate(ZonedDateTime.from(endDate));
//        return goalRepository.save(goal);


}

