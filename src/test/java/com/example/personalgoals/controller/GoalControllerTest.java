package com.example.personalgoals.controller;

import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.service.GoalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoalController.class)
class GoalControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private GoalService goalService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createGoal_ShouldCreateGoalAndReturnCreatedStatus() throws Exception {
        GoalModel goal = new GoalModel();
        GoalModel savedGoal = new GoalModel();
        when(goalService.createGoal(any(GoalModel.class))).thenReturn(savedGoal);
        mockMvc.perform(post("/goals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goal)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedGoal)));
    }

    @Test
    void updateGoal_ShouldUpdateGoalAndReturnNewGoal() throws Exception {
        GoalModel goal = new GoalModel();
        GoalModel newGoal = new GoalModel();
        when(goalService.updateGoal(any(Long.class), any(GoalModel.class))).thenReturn(Optional.of(newGoal));
        mockMvc.perform(put("/goals/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goal)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newGoal)));
    }

    @Test
    public void findGoalById_ShouldFindGoalWithProvidedId() throws Exception {
        // Given
        Long goalId = 1L;
        GoalModel foundGoal = new GoalModel();
        foundGoal.setId(goalId);
        foundGoal.setGoalName("Test Goal");
        when(goalService.findGoalById(goalId)).thenReturn(Optional.of(foundGoal));

        // When/Then
        mockMvc.perform(get("/goals/{id}", goalId))

                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(foundGoal)));
    }

    @Test
    public void findGoalById_ShouldReturnNotFoundForNonExistingGoal() throws Exception {
        // Given
        Long goalId = 2L;
        Mockito.when(goalService.findGoalById(goalId)).thenReturn(Optional.empty());

        // When/Then
        mockMvc.perform(get("/goals/{id}", goalId))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteGoal() throws Exception {

        Long goalId = 1L;

        // Konfiguracja zachowania metody deleteGoal w mocku goalService
        doNothing().when(goalService).deleteGoal(goalId);

        // Wywołanie żądania usuwania z użyciem mockMVC
        mockMvc.perform(delete("/goals/{id}", goalId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}

