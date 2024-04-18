package com.restart.orderservice.repository;

import com.restart.orderservice.model.Order;
import com.restart.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
