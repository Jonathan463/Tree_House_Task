package com.treehouseapp.service.serviceInterfaces;


import com.treehouseapp.dto.CartDTO;
import com.treehouseapp.payload.CartResponse;
import com.treehouseapp.payload.ViewCartResponse;
import com.treehouseapp.model.users.User;
import com.treehouseapp.payload.AllCartItems;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {

    void createCartForUser(User user);

//    void emptyUserCartAfterOrder(User user, Order order);

    ResponseEntity<?> addToCart(CartDTO cartDTO, Long userId, Long menuId);

    ResponseEntity<?> reduceCartItemQty(Long userId, Long menuId);

    ResponseEntity<?> deleteCartItem(Long userId, Long menuId);
    ResponseEntity<AllCartItems> findAllProductsByUser(Long userId);
    List<ViewCartResponse> getAllProductsByUser( Long userId);

    ResponseEntity<CartResponse> updateCartQuantity(Long userId, Long menuId, Integer qty);

//    public ResponseEntity<List<CartItem>> findAllProductsByUser(Long cartId);
}
