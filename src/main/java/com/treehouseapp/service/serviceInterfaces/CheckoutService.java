package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.dto.ShippingAddressDTO;
import com.treehouseapp.model.users.User;
import com.treehouseapp.payload.CheckoutResponse;
import com.treehouseapp.payload.ProductSummary;

import java.util.List;

public interface CheckoutService {
    List<String> saveShippingAddress(long userId, ShippingAddressDTO shippingAddressDTO);
    CheckoutResponse createOrderFromCartItem(long userId, long cartId);
    List<ProductSummary> getAllCartItems(long userId, long cartId);
    ShippingAddressDTO getDefaultShippingAddress(User user);
    List<ShippingAddressDTO> getAllAddress(long userId);
}
