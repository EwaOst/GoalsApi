package com.example.personalgoals.service;

import com.example.personalgoals.model.Category;
import com.example.personalgoals.model.GoalModel;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;


    @Test
    void createUser() {

        //GIVEN
        UserModel userModel = new UserModel();
        userModel.setUserName("Paweł Kowalski");

        //WHEN
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);
        UserModel result = userService.createUser(userModel);

        //THEN
        assertNotNull(result);
        assertEquals("Paweł Kowalski", result.getUserName());
    }

    @Test
    void getUser() {

        //GIVEN
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUserName("Stefan Brzęczyszczykiewicz");

        //WHEN
        when(userRepository.findById(1L)).thenReturn(Optional.of(userModel));

        Optional<UserModel> result = userService.findUserById(1L);

        //THEN
        assertTrue(result.isPresent());
        assertEquals("Stefan Brzęczyszczykiewicz", result.get().getUserName());

    }

    @Test
    void updateUser() {

        //GIVEN
        Long userId = 1L;
        UserModel existingUser = new UserModel();


        existingUser.setId(userId);
        existingUser.setUserName("Old User Name");
        existingUser.setEmail("ere@dsds.pl");
        existingUser.setAge("22");

        UserModel updatedUser = new UserModel();
        updatedUser.setUserName("New User Name");
        updatedUser.setEmail("dsfds@dsds.pl");
        updatedUser.setAge("21");


        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //WHEN
        Optional<UserModel> result = userService.updateUser(userId, updatedUser);

        //THEN
        assertTrue(result.isPresent());
        assertEquals(updatedUser.getUserName(), result.get().getUserName());
        assertEquals(updatedUser.getEmail(), result.get().getEmail());
        assertEquals(updatedUser.getAge(), result.get().getAge());


    }

    @Test
    void deleteUser_ShouldDeleteWhenFound() {

        //GIVEN
        Long id = 1L;

        // Konfiguracja zachowań mocków
        when(userRepository.existsById(id)).thenReturn(true);

        // Wywołanie metody testowanej
        userService.deleteUser(id);

        // Weryfikacja, czy metoda deleteById została wywołana
        verify(userRepository).deleteById(id);
    }

    @Test
    void deleteUser_ShouldThrowExceptionWhenNotFound() {
        //GIVEN
        Long id = 1L;

        //WHEN
        when(userRepository.existsById(id)).thenReturn(false);

        //THEN
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(id));
    }

}
