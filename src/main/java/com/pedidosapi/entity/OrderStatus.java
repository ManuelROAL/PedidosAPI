package com.pedidosapi.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "order_status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;
}