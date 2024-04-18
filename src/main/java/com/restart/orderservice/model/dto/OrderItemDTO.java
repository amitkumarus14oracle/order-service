package com.restart.orderservice.model.dto;

import com.restart.orderservice.model.OrderItem;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private Long itemId;
    private int quantity;

    public static OrderItemDTO from(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProductId());
        dto.setQuantity(item.getQuantity());
        dto.setItemId(item.getItemId());
        return dto;
    }
}
