package com.foodOrder.FoodOrder.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDTO {

    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be null")
    private String password;

}
