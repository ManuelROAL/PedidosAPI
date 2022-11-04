package com.pedidosapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "order_tracking", indexes = {
        @Index(name = "fk_order_tracking_order_status1_idx", columnList = "order_status_id"),
        @Index(name = "fk_order_tracking_orders1_idx", columnList = "order_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "date", nullable = false)
    private Instant date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;
}