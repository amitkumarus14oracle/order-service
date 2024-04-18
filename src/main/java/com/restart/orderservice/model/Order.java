package com.restart.orderservice.model;

import com.restart.orderservice.model.dto.OrderDTO;
import com.restart.orderservice.model.dto.OrderItemDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ord")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@OneToMany(mappedBy = "order")
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderItem> items = new HashSet<>();
    private OrderStatus status;

    public static Order from(OrderDTO dto) {
        Order order = new Order();
        order.setItems(dto.getItems().stream().map(OrderItem::from).collect(Collectors.toSet()));
        order.setStatus(dto.getStatus());
        return order;
    }
}
