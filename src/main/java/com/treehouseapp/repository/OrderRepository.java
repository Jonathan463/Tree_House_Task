package com.treehouseapp.repository;

import com.treehouseapp.model.enums.OrderStatus;
import com.treehouseapp.model.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderById(Long id);
    Optional<Order>findOrderByIdAndUserId(Long orderId, Long userId);
    List<Order> findAllByUserId(Long userId);
    Optional<Order> getOrderByIdIsAndUserIdIs(Long orderId, Long userId);
    Optional<Order> findByUserIdAndStatus(Long user_id, OrderStatus status);

    List<Order> findByUserId(Long userId);

    @Query(value="SELECT count(o) FROM orders o WHERE o.status = :status",nativeQuery = true)
    Long countOrderByOrderStatusEquals(@Param("status") String status);

    @Query(value="SELECT count(o) FROM orders o WHERE o.order_date =  current_date",nativeQuery = true)
    Long countOrdersByOrderDateEquals();

    @Query("SELECT sum(o.amount) FROM Order o")
    Double sumTotalOrderAmount();



}
