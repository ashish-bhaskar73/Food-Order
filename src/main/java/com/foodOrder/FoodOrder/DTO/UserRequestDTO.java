package com.foodOrder.FoodOrder.DTO;

import com.foodOrder.FoodOrder.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class UserRequestDTO {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String phoneNumber;
    private String address;

    @NotBlank(message = "Role can't be null")
    private Role role;
}
