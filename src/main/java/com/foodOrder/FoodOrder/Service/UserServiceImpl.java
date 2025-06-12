package com.foodOrder.FoodOrder.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.DTO.UserLoginRequestDTO;
import com.foodOrder.FoodOrder.DTO.UserRequestDTO;
import com.foodOrder.FoodOrder.DTO.UserResponseDTO;
import com.foodOrder.FoodOrder.Exception.DuplicateResourceException;
import com.foodOrder.FoodOrder.Exception.InvalidRequestException;
import com.foodOrder.FoodOrder.Exception.ResourceNotFoundException;
import com.foodOrder.FoodOrder.Repository.UserRepository;
import com.foodOrder.FoodOrder.Security.JwtUtil;
import com.foodOrder.FoodOrder.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public final class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwt;

    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO response = new UserResponseDTO();
        response.setUserId(user.getUserId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUsername(user.getUsername());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAddress(user.getAddress());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        return response;

    }

    private User convertToEntity(UserRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        String passwordEncoded = passwordEncoder.encode(request.getPassword());
        user.setPassword(passwordEncoded);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        return user;
    }

    // request for create user4
    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User with email already exists: " + request.getEmail());
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + request.getUsername());
        }
        User user = convertToEntity(request);
        User savedUser = userRepository.save(user);
        return convertToResponseDTO(savedUser);
    }

    // loginRequest
    @Override
    public String loginUser(UserLoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("User not present in system! Please Register first. Thankyou"));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwt.generateToken(request.getUsername());
            return "User logged successfully" + request.getUsername() + "Bearer: " + token;
        } else {
            return "Invalid password. Please try again.";
        }
    }

    // request for get user by id
    @Override
    public UserResponseDTO getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + id));

        return convertToResponseDTO(user);
    }

    // request to fecth all users
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToResponseDTO).collect(Collectors.toList());
    }

    // request to update user using id
    @Override
    public UserResponseDTO updateUserById(int id, UserRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found: " + id));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        // when setting authentication then update the logic
        if (request.getUsername() != null || request.getRole() != null) {
            throw new InvalidRequestException("Only admin can update the role and username of a user");
        } else {
            if (request.getUsername() != null) {
                user.setUsername(request.getUsername());
            }

            if (request.getRole() != null) {
                user.setRole(request.getRole());
            }
        }

        return convertToResponseDTO(userRepository.save(user));
    }

    // request to delete user by id
    @Override
    public void deleteUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Can't be deleted: " + id));

        userRepository.delete(user);

    }
}