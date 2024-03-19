package com.example.personalgoals.controller;

import com.example.personalgoals.dto.UserReqDto;
import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;

    @PostMapping
    public ResponseEntity<GoalModel> createGoal(@RequestBody GoalModel goal) {
        GoalModel newGoal = goalService.createGoal(goal);
        return new ResponseEntity<>(newGoal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoalModel> findGoalById(@PathVariable Long id) {
        return goalService.findGoalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id, @RequestBody GoalModel goal) {
        return goalService.findGoalById(id).map(updateGoal -> {
            updateGoal.setGoalName(goal.getGoalName());
            updateGoal.setCategory(goal.getCategory());
            updateGoal.setStartDate(goal.getStartDate());
            updateGoal.setEndDate(goal.getEndDate());
            GoalModel updatedGoal = goalService.createGoal(updateGoal);
            return ResponseEntity.ok(updatedGoal);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<GoalModel> updateGoalCategory(@PathVariable Long id, @RequestParam String category) {
        GoalModel updatedGoal = goalService.updateGoalCategory(id, category);
        return ResponseEntity.ok(updatedGoal);
    }

    @PutMapping("/{id}/dates")
    public ResponseEntity<GoalModel> updateGoalDates(@PathVariable Long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        GoalModel updatedGoal = goalService.updateGoalDates(id, startDate, endDate);
        return ResponseEntity.ok(updatedGoal);
    }
}