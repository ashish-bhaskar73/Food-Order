package com.foodOrder.FoodOrder.DTO;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

    private int orderId;
    private String orderStatus;
    private double totalAmount;
    private Date orderDate;
    private List<OrderItemInfo> orderItemInfo;

    @Getter
    @Setter
    public static class OrderItemInfo {

        private String itemName;
        private int quantity;
        private double price;
    }

}
