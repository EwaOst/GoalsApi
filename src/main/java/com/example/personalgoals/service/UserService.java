package com.example.personalgoals.service;

import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserModel createUser(@NotBlank(message = "Name is required") String userModel) {
        UserModel newUserModel = new UserModel();
        newUserModel = userRepository.save(newUserModel);
        return newUserModel;
    }

    public Optional<UserModel> getUser(Long id) {
        return userRepository.findById(id);
    }

    public UserModel updateUser(Long id, String newUserName) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUserName(newUserName);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)){
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);

    }
}
