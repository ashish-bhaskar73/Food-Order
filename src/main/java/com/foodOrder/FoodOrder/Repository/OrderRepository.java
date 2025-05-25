package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.foodOrder.FoodOrder.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer>{
    
}
