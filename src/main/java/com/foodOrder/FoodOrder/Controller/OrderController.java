package com.foodOrder.FoodOrder.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodOrder.FoodOrder.DTO.OrderRequestDTO;
import com.foodOrder.FoodOrder.DTO.OrderResponseDTO;
import com.foodOrder.FoodOrder.Service.OrderService;
import com.foodOrder.FoodOrder.model.Orders;

@RestController
@RequestMapping("/api/v1/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    // Post create order
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO requestDTO) {
        return new ResponseEntity<>(
                orderService.createOrder(requestDTO),
                HttpStatus.CREATED);
    }

    // get all orders

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrder() {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    // get order by orderId
    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    // cancel order by customer
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Orders> cancelOrder(@PathVariable Integer orderId) {
        return new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK);
    }

}
