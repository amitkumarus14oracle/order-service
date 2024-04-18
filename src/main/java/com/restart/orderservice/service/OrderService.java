package com.restart.orderservice.service;

import com.restart.orderservice.model.Order;
import com.restart.orderservice.model.OrderItem;
import com.restart.orderservice.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private OrderRepository orderRepository;

    public OrderService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public Order  createOrder(Order order) {
        for (OrderItem item : order.getItems()) {
            if (!entityManager.contains(item)) {
                // If the OrderItem is not managed (detached), you can handle it here
                // For example, log a warning or take corrective action
                // You can also re-attach the entity if necessary
                // entityManager.merge(item);
            }
        }
        // Save the order to the database
        Order savedOrder = orderRepository.save(order);

        // Publish a message to Kafka to notify the InventoryService
        kafkaTemplate.send("order-created", savedOrder.getId().toString(), savedOrder);

        return savedOrder;
    }

    @KafkaListener(topics = "order-reversed", groupId = "order")
    @Transactional
    public void reverseOrder(@Payload Order order) {
        try{
            Optional<Order> ord = orderRepository.findById(order.getId());
            ord.ifPresent( o -> {
                o.setStatus(order.getStatus());
                orderRepository.save(o);
            });
        }catch (Exception ex) {
            System.out.println("Exception while reverting the order");
        }
    }
}