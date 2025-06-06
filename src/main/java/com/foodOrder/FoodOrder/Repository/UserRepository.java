package com.foodOrder.FoodOrder.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodOrder.FoodOrder.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    // sql query to check user exist or not by username
    boolean existsByUsername(String username);

    // sql query to check user exist or not by email id
    boolean existsByEmail(String email);
}
