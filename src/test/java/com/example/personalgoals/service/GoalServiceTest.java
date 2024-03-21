package com.example.personalgoals.service;

import com.example.personalgoals.model.Category;
import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.HabitModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.GoalRepository;
import com.example.personalgoals.repository.HabitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class GoalServiceTest {
    @Mock
    private GoalRepository goalRepository;
    @InjectMocks
    private GoalService goalService;
    @Test
    void createGoal() {
        //GIVEN
        GoalModel goalModel = new GoalModel();
        goalModel.setGoalName("Nauka pływania");

        //WHEN
        when(goalRepository.save(any(GoalModel.class))).thenReturn(goalModel);
        GoalModel result = goalService.createGoal(goalModel);

        //THEN
        assertNotNull(result);
        assertEquals("Nauka pływania", result.getGoalName());
    }

    @Test
    void findGoalById() {
        GoalModel goalModel = new GoalModel();
        goalModel.setId(1L);
        goalModel.setGoalName("Nauka pływania");

        //WHEN
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goalModel));
        Optional<GoalModel> result = goalService.findGoalById(1L);

        //THEN
        assertTrue(result.isPresent());
        assertEquals("Nauka pływania", result.get().getGoalName());

    }

    @Test
    void updateGoal() {

        // Given
        Long goalId = 1L;
        GoalModel existingGoal = new GoalModel();
        existingGoal.setId(goalId);
        existingGoal.setGoalName("Nauczyć się pływać");
        existingGoal.setCategory(Category.SPORT);
        existingGoal.setStartDate(ZonedDateTime.of(LocalDate.of(2024, 2, 15), LocalTime.MIDNIGHT, ZoneId.systemDefault()));
        existingGoal.setEndDate(ZonedDateTime.of(LocalDate.of(2024, 11, 21), LocalTime.MIDNIGHT, ZoneId.systemDefault()));

        GoalModel updatedGoal = new GoalModel();
        updatedGoal.setGoalName("Zacząć medytować");
        updatedGoal.setCategory(Category.SPIRITUAL);
        updatedGoal.setStartDate(ZonedDateTime.of(LocalDate.of(2024, 1, 1), LocalTime.MIDNIGHT, ZoneId.systemDefault()));
        updatedGoal.setEndDate(ZonedDateTime.of(LocalDate.of(2024, 5, 30), LocalTime.MIDNIGHT, ZoneId.systemDefault()));

        when(goalRepository.findById(goalId)).thenReturn(Optional.of(existingGoal));
        when(goalRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

// When
        Optional<GoalModel> result = goalService.updateGoal(goalId, updatedGoal);

// Then
        assertTrue(result.isPresent());
        assertEquals(updatedGoal.getGoalName(), result.get().getGoalName());
        assertEquals(updatedGoal.getCategory(), result.get().getCategory());
        assertEquals(updatedGoal.getStartDate(), result.get().getStartDate());
        assertEquals(updatedGoal.getEndDate(), result.get().getEndDate());
    }

    @Test
    void deleteGoal() {
        //GIVEN
        Long id = 1L;

        // Konfiguracja zachowań mocków
        when(goalRepository.existsById(id)).thenReturn(true);

        // Wywołanie metody testowanej
        goalService.deleteGoal(id);

        // Weryfikacja, czy metoda deleteById została wywołana
        verify(goalRepository).deleteById(id);

    }

//    @Test
//    void updateGoalCategory() {
//    }
//
//    @Test
//    void updateGoalDates() {
//    }
}