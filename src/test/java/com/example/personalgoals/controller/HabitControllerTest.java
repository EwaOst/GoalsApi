package com.example.personalgoals.controller;

import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.HabitModel;
import com.example.personalgoals.service.GoalService;
import com.example.personalgoals.service.HabitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(HabitController.class)
class HabitControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private HabitService habitService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createHabit() throws Exception {

       HabitModel habit = new HabitModel();
        HabitModel savedHabit = new HabitModel();
        when(habitService.createHabit(any(HabitModel.class))).thenReturn(savedHabit);
        mockMvc.perform(post("/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(habit)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedHabit)));
    }
}