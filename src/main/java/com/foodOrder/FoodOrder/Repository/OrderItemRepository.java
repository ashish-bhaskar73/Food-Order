package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer>{
    
}
