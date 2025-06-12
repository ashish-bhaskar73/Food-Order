package com.foodOrder.FoodOrder.DTO;

import com.foodOrder.FoodOrder.model.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Role role;
}
