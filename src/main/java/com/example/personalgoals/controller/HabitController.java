package com.example.personalgoals.controller;

import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.HabitModel;
import com.example.personalgoals.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/habits")
public class HabitController {

    private final HabitService habitService;


    @PostMapping
    public ResponseEntity<HabitModel> createHabit(@RequestBody HabitModel habit) {
        HabitModel newHabit = habitService.createHabit(habit);
        return new ResponseEntity<>(newHabit, HttpStatus.CREATED);
    }

}
