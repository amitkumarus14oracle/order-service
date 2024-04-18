package com.restart.orderservice.controller;

import com.restart.orderservice.model.Order;
import com.restart.orderservice.model.OrderItem;
import com.restart.orderservice.model.dto.OrderDTO;
import com.restart.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderDTO order) {
        Order placedOrder = orderService.createOrder(Order.from(order));
        //Order placedOrder = orderService.createOrder(order);
        return ResponseEntity.ok().body(placedOrder);
    }
}
