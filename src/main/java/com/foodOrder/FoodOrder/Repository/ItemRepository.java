package com.foodOrder.FoodOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.FoodOrder.model.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{
   Item findByName(String name);
}
