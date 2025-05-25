package com.foodOrder.FoodOrder.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.Exception.DuplicateResourceException;
import com.foodOrder.FoodOrder.Exception.ResourceNotFoundException;
import com.foodOrder.FoodOrder.Repository.UserRepository;
import com.foodOrder.FoodOrder.model.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // register user
    public User registerUser(User user) {
        boolean userExist = userRepository.existsById(user.getUserId());
        if (userExist) {
            throw new DuplicateResourceException("user is already present");
        }
        return userRepository.save(user);
    }

    // get all usser
    public List<User> getAllUsers() {
        // long userCount = userRepository.count();
        // if (userCount == 0) {
        //     throw new ResourceNotFoundException("no user is present");
        // }
        return userRepository.findAll();
    }

    // get user by id
    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found by Id:" + userId));
    }

    // update user
    public User updateUser(User user, int userId) {
        User updatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found by Id:" + userId));

        if (user.getAddress() != null && !user.getAddress().isBlank()) {
            updatedUser.setAddress(user.getAddress());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            updatedUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            updatedUser.setPassword(user.getPassword());
        }

        if (user.getPhoneNumber() != null && !user.getPhoneNumber().isBlank()) {
            updatedUser.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getUsername() != null && !user.getUsername().isBlank()) {
            updatedUser.setUsername(user.getUsername());
        }
        return userRepository.save(updatedUser);

    }

    // delete User
    public void deleteUser(int userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

}



// part 2 for updateion use DTO for safer side