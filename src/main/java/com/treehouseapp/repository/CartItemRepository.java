package com.treehouseapp.repository;

import com.treehouseapp.model.carts.Cart;
import com.treehouseapp.model.carts.CartItem;
import com.treehouseapp.model.flowers.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> , PagingAndSortingRepository<CartItem,Long> {

    Optional<CartItem> findByMenuIdAndCart(MenuItem menu, Cart cart);
    List<CartItem> findAllByCart(Cart cart);
    List<CartItem> findAllByCart_Id(Long cartId);
    List<CartItem> findAllByMenuId_Id(Long menuId);

}
