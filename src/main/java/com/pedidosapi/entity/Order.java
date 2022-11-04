package com.pedidosapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "`order`", indexes = {
        @Index(name = "fk_orders_user_idx", columnList = "user_id"),
        @Index(name = "fk_order_order_status1_idx", columnList = "order_status_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_status_id", nullable = false)
    private OrderStatus orderStatus;
}