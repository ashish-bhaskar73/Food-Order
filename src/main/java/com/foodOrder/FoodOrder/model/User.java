package com.foodOrder.FoodOrder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @NotBlank(message = "user name is required")
    private String username;
    @Email(message = "mail should be correct example: abc@gmail.com")
    private String email;
    @NotBlank(message = "Password should be required")
    private String password;
    private String phoneNumber;
    private String address;
}
