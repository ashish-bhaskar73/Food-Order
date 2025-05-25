package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    
}
