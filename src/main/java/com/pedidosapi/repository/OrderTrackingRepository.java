package com.pedidosapi.repository;

import com.pedidosapi.entity.OrderTracking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTrackingRepository extends CrudRepository<OrderTracking, Integer> {

}
