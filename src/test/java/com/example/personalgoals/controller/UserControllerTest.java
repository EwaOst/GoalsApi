package com.example.personalgoals.controller;


import com.example.personalgoals.controller.UserController;
import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void createUser_ShouldCreateUserAndReturnCreatedStatus() throws Exception {
        UserModel user = new UserModel();
        UserModel savedUser = new UserModel();
        when(userService.createUser(any(UserModel.class))).thenReturn(savedUser);
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(savedUser)));
    }

    @Test
    void updateUser_ShouldUpdateUserAndReturnNewUser() throws Exception {
        UserModel user = new UserModel();
        UserModel newUser = new UserModel();
        when(userService.updateUser(any(Long.class), any(UserModel.class))).thenReturn(Optional.of(newUser));
        mockMvc.perform(put("/user/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newUser)));
    }

    @Test
    public void findUserById_ShouldFindUserWithProvidedId() throws Exception {
        // Given
        Long userId = 1L;
        UserModel foundUser = new UserModel();
        foundUser.setId(userId);
        foundUser.setUserName("Jan Kowaslski");
        when(userService.findUserById(userId)).thenReturn(Optional.of(foundUser));

        // When/Then
        mockMvc.perform(get("/user/{id}", userId))

                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(foundUser)));
    }

    @Test
    public void findUserById_ShouldReturnNotFoundForNonExistingGoal() throws Exception {
        // Given
        Long userId = 2L;
        Mockito.when(userService.findUserById(userId)).thenReturn(Optional.empty());

        // When/Then
        mockMvc.perform(get("/user/{id}", userId))
                .andExpect(status().isNotFound());

    }

    @Test
    void deleteUser() throws Exception {

        Long userId = 1L;

        // Konfiguracja zachowania metody deleteGoal w mocku goalService
        doNothing().when(userService).deleteUser(userId);

        // Wywołanie żądania usuwania z użyciem mockMVC
        mockMvc.perform(delete("/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}