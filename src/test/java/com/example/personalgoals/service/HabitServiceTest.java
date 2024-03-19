package com.example.personalgoals.service;

import com.example.personalgoals.model.HabitModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.HabitRepository;
import com.example.personalgoals.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class HabitServiceTest {

    @Mock
    private HabitRepository habitRepository;
    @InjectMocks
    private HabitService habitService;

    @Test
    void createHabit() {

        //GIVEN
        HabitModel habitModel = new HabitModel();
        habitModel.setHabitName("Długie spanie");

        //WHEN
        when(habitRepository.save(any(HabitModel.class))).thenReturn(habitModel);
        HabitModel result = habitService.createHabit(habitModel);

        //THEN
        assertNotNull(result);
        assertEquals("Długie spanie", result.getHabitName());
    }
}