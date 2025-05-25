package com.foodOrder.FoodOrder.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodOrder.FoodOrder.DTO.OrderRequestDTO;
import com.foodOrder.FoodOrder.DTO.OrderResponseDTO;
import com.foodOrder.FoodOrder.Exception.InvalidRequestException;
import com.foodOrder.FoodOrder.Exception.ResourceNotFoundException;
import com.foodOrder.FoodOrder.Repository.ItemRepository;
import com.foodOrder.FoodOrder.Repository.OrderRepository;
import com.foodOrder.FoodOrder.Repository.RestaurantRepository;
import com.foodOrder.FoodOrder.Repository.UserRepository;
import com.foodOrder.FoodOrder.model.Item;
import com.foodOrder.FoodOrder.model.Orders;
import com.foodOrder.FoodOrder.model.Restaurant;
import com.foodOrder.FoodOrder.model.User;
import com.foodOrder.FoodOrder.model.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    // create an order
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        // check user exist or not

        User user = userRepository.findById(orderRequestDTO.getUserId()).orElseThrow(
                () -> new InvalidRequestException("user with userId does not exist: " + orderRequestDTO.getUserId()));

        // check restaurant exist or not
        Restaurant restaurant = restaurantRepository.findById(orderRequestDTO.getRestaurantId())
                .orElseThrow(() -> new InvalidRequestException(
                        "restaurant with restaurant i does not exist: " + orderRequestDTO.getRestaurantId()));

        // create new order
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setRestaurant(restaurant);
        orders.setOrderDate(new Date());
        orders.setOrderStatus("Pending");

        double totalAmount = 0;

        // create orderitem list
        List<OrderItem> orderItems = new ArrayList<>();
        // check requested item is present or not
        for (OrderRequestDTO.OrderItemDTO itemDTO : orderRequestDTO.getItems()) {
            Item item = itemRepository.findById(itemDTO.getItemId()).orElseThrow(
                    () -> new ResourceNotFoundException("Item not present with itemId: " + itemDTO.getItemId()));

            if (itemDTO.getQuantity() > item.getQuantity()) {
                throw new ResourceNotFoundException("Out Of Stock item: " + itemDTO.getItemId());
            }

            item.setQuantity(item.getQuantity() - itemDTO.getQuantity());
            itemRepository.save(item);

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(orders);
            orderItems.add(orderItem);

            totalAmount += item.getPrice() * itemDTO.getQuantity();
        }
        orders.setOrderItems(orderItems);
        orders.setTotalAmount(totalAmount);

        Orders savedOrder = orderRepository.save(orders);
        return mapToOrderResponseDTO(savedOrder);
    }

    private OrderResponseDTO mapToOrderResponseDTO(Orders savedOrder) {
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderDate(savedOrder.getOrderDate());
        orderResponseDTO.setOrderId(savedOrder.getOrderId());
        orderResponseDTO.setOrderStatus(savedOrder.getOrderStatus());
        orderResponseDTO.setTotalAmount(savedOrder.getTotalAmount());

        List<OrderResponseDTO.OrderItemInfo> items = savedOrder.getOrderItems().stream().map(orderItem -> {
            OrderResponseDTO.OrderItemInfo info = new OrderResponseDTO.OrderItemInfo();
            info.setItemName(orderItem.getItem().getName());
            info.setQuantity(orderItem.getQuantity());
            info.setPrice(orderItem.getItem().getPrice());
            return info;
        }).toList();

        orderResponseDTO.setOrderItemInfo(items);
        return orderResponseDTO;
    }

    // get all order
    public List<OrderResponseDTO> getAllOrder() {
        return orderRepository.findAll().stream().map(order -> {
            OrderResponseDTO dto = new OrderResponseDTO();
            dto.setOrderStatus(order.getOrderStatus());
            dto.setOrderId(order.getOrderId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setOrderDate(order.getOrderDate());
            return dto;
        }).collect(Collectors.toList());
    }

    // get order by id
    public Orders getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order not found: " + orderId));
    }

    // Cancel order
    @Transactional
    public Orders cancelOrder(int orderId) {

        /*
         * if orderstatus is pending
         * and customer wants to cancel the order than set order status as cancelled
         */

        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order not found: " + orderId));
        if (!"Pending".equalsIgnoreCase(order.getOrderStatus())) {
            throw new IllegalStateException("Only pending orders can be cancelled");
        }
        for (OrderItem orderItem : order.getOrderItems()) {
            Item item = orderItem.getItem();
            item.setQuantity(orderItem.getQuantity() + item.getQuantity());
            itemRepository.save(item);
        }
        order.setOrderStatus("Cancelled");

        return orderRepository.save(order);
    }

}
