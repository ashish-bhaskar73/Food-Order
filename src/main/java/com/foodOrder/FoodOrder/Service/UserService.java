package com.foodOrder.FoodOrder.Service;

import java.util.List;

import com.foodOrder.FoodOrder.DTO.UserRequestDTO;
import com.foodOrder.FoodOrder.DTO.UserResponseDTO;


public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(int id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserById(int id, UserRequestDTO request);

    void deleteUserById(int id);
}
