package com.example.personalgoals.service;

import com.example.personalgoals.model.UserModel;
import com.example.personalgoals.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserModel createUser(UserModel user) {
        UserModel newUser = new UserModel();
        newUser = userRepository.save(newUser);
        return newUser;
    }

    public Optional<UserModel> findUserById(Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user;
        } else {
            throw new EntityNotFoundException("Goal not found");
        }
    }


    public Optional<UserModel> updateUser(Long id, UserModel updateUser) {
        return Optional.ofNullable(userRepository.findById(id)
                .map(user -> {
                    user.setUserName(updateUser.getUserName());
                    user.setEmail(updateUser.getEmail());
                    user.setAge(updateUser.getAge());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User does not exist")));
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);

    }
}
