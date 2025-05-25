package com.foodOrder.FoodOrder.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.Exception.ResourceNotFoundException;
import com.foodOrder.FoodOrder.Repository.RestaurantRepository;
import com.foodOrder.FoodOrder.model.Restaurant;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // register restaturant

    public Restaurant register(Restaurant restaurant) {
        boolean exists = restaurantRepository.existsById(restaurant.getRestaurantId());
        if (exists) {
            throw new ResourceNotFoundException("Restaurant not found: " + restaurant.getRestaurantId());
        }

        return restaurantRepository.save(restaurant);
    }

    // get restarturant by name

    public List<Restaurant> getRestaurantByName(String restaurantName) {
        List<Restaurant> restaurants = restaurantRepository.findByRestaurantName(restaurantName);
        if (restaurants.isEmpty()) {
            throw new ResourceNotFoundException("Restaurant Not found: " + restaurantName);
        }
        return restaurants;
    }

    // get all restaurants

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // update restaurant based on id

    public Restaurant updateRestaurant(int restarturantId, Restaurant restaurant) {
        Restaurant exits = restaurantRepository.findById(restarturantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not found: " + restarturantId));
        if (restaurant.getLocation() != null && !restaurant.getLocation().isBlank()) {
            exits.setLocation(restaurant.getLocation());
        }

        if (restaurant.getRestaurantName() != null && !restaurant.getLocation().isBlank()) {
            exits.setRestaurantName(restaurant.getRestaurantName());
        }
        if (restaurant.getCuisineType() != null && !restaurant.getLocation().isBlank()) {
            exits.setCuisineType(restaurant.getCuisineType());
        }

        return restaurantRepository.save(exits);
    }

    // delete restaturant

    public void deleteRestaurant(int restarturantId) {

        restaurantRepository.findById(restarturantId)
                .orElseThrow(() -> new ResourceNotFoundException("restaurant not found: " + restarturantId));
        restaurantRepository.deleteById(restarturantId);
    }


    //orders list

    
}
