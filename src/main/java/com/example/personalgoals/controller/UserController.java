package com.example.personalgoals.controller;

import com.example.personalgoals.dto.UserReqDto;
import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> createUser(@Valid @RequestBody UserReqDto request) {
        UserModel user = userService.createUser(request.getUserName());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, String newUserName, @Valid @RequestBody UserReqDto request) {
        UserModel updateUser = userService.updateUser(id,newUserName);
        return ResponseEntity.ok(updateUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}