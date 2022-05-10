package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.dto.ResponseViewUserOrdersDTO;
import com.treehouseapp.model.flowers.MenuItem;
import com.treehouseapp.payload.AllCartItems;
import com.treehouseapp.payload.UpdatePayLoad;
import com.treehouseapp.payload.ViewOrderDetailsResponse;

import java.util.List;

public interface OrderService {

   ViewOrderDetailsResponse getOrderDetails(Long userId, Long orderId);
    List<MenuItem> getOrderSummary(Long userId);
    ResponseViewUserOrdersDTO getAllOrdersByUserId(Long userId);
    AllCartItems checkoutCartItems(Long userId);
    UpdatePayLoad updateOrderStatus(Long orderId);
}
