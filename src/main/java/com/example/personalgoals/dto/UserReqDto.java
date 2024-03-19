package com.example.personalgoals.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReqDto {

    @NotBlank(message = "Name is required")
    private String userName;


}
