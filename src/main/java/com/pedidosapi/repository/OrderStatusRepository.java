package com.pedidosapi.repository;

import com.pedidosapi.entity.OrderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {

}
