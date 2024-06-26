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
        return goalService.updateGoal(id, goal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound()
                        .build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return ResponseEntity.noContent().build();
    }

}