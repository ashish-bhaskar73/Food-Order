package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    
}
