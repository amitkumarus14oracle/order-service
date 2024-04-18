package com.restart.orderservice.model.dto;

import com.restart.orderservice.model.OrderItem;
import com.restart.orderservice.model.OrderStatus;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private Set<OrderItemDTO> items = new HashSet<>();
    private OrderStatus status;
}
