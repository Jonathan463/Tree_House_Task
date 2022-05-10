package com.treehouseapp.repository;

import com.treehouseapp.model.orders.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Long> {

    Optional<OrderDetail> findOrderDetailByOrderIdAndMenuId(Long orderId, Long menuId);
    //findOrderDetailByOrderIdAndMenuId(Long orderId, Long menuId);

    Optional<OrderDetail> findOrderDetailById(Long orderId);

    List<OrderDetail> findAllByOrderId(Long orderId);
    List<OrderDetail> findAllByOrderIdEquals(Long orderId);
    List<OrderDetail> findAllByOrder_Id(Long orderId);
    void deleteAllByOrder_Id(Long orderId);

}

