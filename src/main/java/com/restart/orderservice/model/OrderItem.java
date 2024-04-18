package com.restart.orderservice.model;

import com.restart.orderservice.model.dto.OrderItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private Long productId;

    //@ManyToOne
    //@JoinColumn(name = "id", nullable = false)
    //private Order order;
    private int quantity;
    // getters and setters

    public static OrderItem from(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setProductId(orderItemDTO.getProductId());
        return orderItem;
    }
}