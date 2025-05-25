package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.Restaurant;
import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
    List<Restaurant> findByRestaurantName(String restaurantName);
}
