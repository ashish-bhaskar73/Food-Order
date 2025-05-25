package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.User;


public interface  UserRepository extends JpaRepository<User, Integer> {
    
}
