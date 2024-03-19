package com.example.personalgoals.service;

import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.HabitModel;
import com.example.personalgoals.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    public HabitModel createHabit(HabitModel habit) {
        HabitModel newHabit = new HabitModel();
        newHabit = habitRepository.save(newHabit);
        return newHabit;
    }
}
