package com.example.personalgoals.repository;

import com.example.personalgoals.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long>  {
}
