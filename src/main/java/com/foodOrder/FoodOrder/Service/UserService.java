package com.foodOrder.FoodOrder.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.DTO.UserRequestDTO;
import com.foodOrder.FoodOrder.DTO.UserResponseDTO;

@Service
public interface UserService {

    UserResponseDTO createUser(UserRequestDTO request);

    UserResponseDTO getUserById(int id);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO updateUserById(int id, UserRequestDTO request);

    void deleteUser(int id);
}
