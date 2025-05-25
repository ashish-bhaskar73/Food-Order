package com.foodOrder.FoodOrder.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.FoodOrder.Service.RestaurantService;
import com.foodOrder.FoodOrder.model.Restaurant;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/register")
    public ResponseEntity<Restaurant> register(@RequestBody @Valid Restaurant restaurant) {
        Restaurant registeRestaurant = restaurantService.register(restaurant);
        return new ResponseEntity<>(registeRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/{restaurantName}")
    public ResponseEntity<List<Restaurant>> getRestaurantByName(@PathVariable String restaurantName) {
        return new ResponseEntity<>(restaurantService.getRestaurantByName(restaurantName), HttpStatus.OK);
    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Integer restaurantId,
            @RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.updateRestaurant(restaurantId, restaurant), HttpStatus.OK);
    }


    @PostMapping("/delete/{restarturantId}")
    public String deleteRestaurant(int restarturantId) {
        restaurantService.deleteRestaurant(restarturantId);
        return "Restaurant deleted: "+ restarturantId + "with restaurant name: " ;
    }


    
}
