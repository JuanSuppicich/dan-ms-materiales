package com.durandsuppicich.danmsmateriales.repository;

import java.util.List;

import com.durandsuppicich.danmsmateriales.domain.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemJpaRepository extends JpaRepository<OrderItem, Integer> {

    // TODO check
    @Query(value = "SELECT * " +
            "FROM ms_orders.order_item oi " +
            "WHERE oi.order_id = :orderId",
            nativeQuery = true
    )
    List<OrderItem> findAllByOrderId(Integer orderId);
 }
