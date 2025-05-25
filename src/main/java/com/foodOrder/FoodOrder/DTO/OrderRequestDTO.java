package com.foodOrder.FoodOrder.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private int userId;
    private int restaurantId;

    private List<OrderItemDTO> items;

    @Getter
    @Setter
    public static class OrderItemDTO {
        private int itemId;
        private int quantity;
    }
}
