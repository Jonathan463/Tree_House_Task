package com.chompfooddeliveryapp.controller;

import com.chompfooddeliveryapp.dto.ShippingAddressDTO;
import com.chompfooddeliveryapp.exception.BadRequestException;
import com.chompfooddeliveryapp.model.carts.Cart;
import com.chompfooddeliveryapp.payload.AllCartItems;
import com.chompfooddeliveryapp.payload.CheckoutResponse;
import com.chompfooddeliveryapp.repository.CartRepository;
import com.chompfooddeliveryapp.service.serviceInterfaces.CartService;
import com.chompfooddeliveryapp.service.serviceInterfaces.CheckoutService;
import com.chompfooddeliveryapp.service.serviceInterfaces.OrderService;
import com.chompfooddeliveryapp.service.serviceInterfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CheckOutController {

    private final OrderService orderService;
    private final CheckoutService checkoutService;
    private final UserServiceInterface userServiceInterface;
    private final CartRepository cartRepository;

    @Autowired
    public CheckOutController(OrderService orderService, CheckoutService checkoutService, UserServiceInterface userServiceInterface, CartService cartService, CartRepository cartRepository) {
        this.orderService = orderService;
        this.checkoutService = checkoutService;
        this.userServiceInterface = userServiceInterface;

        this.cartRepository = cartRepository;
    }

    @GetMapping("/checkout")
    public ResponseEntity<AllCartItems> checkoutOrders() {
        var checkoutItems = orderService.checkoutCartItems(userServiceInterface.getUserIDFromSecurityContext());
        return ResponseEntity.ok().body(checkoutItems);
    }

    public ResponseEntity<List<ShippingAddressDTO>> getUserAddress() {
        return ResponseEntity.ok().body(checkoutService.getAllAddress(userServiceInterface.getUserIDFromSecurityContext()));
    }

    @PostMapping("/new/order")
    public ResponseEntity<CheckoutResponse> createOrder() {
        long userid = userServiceInterface.getUserIDFromSecurityContext();
        Cart cart = cartRepository.getByUser_Id(userid).orElseThrow(()-> new BadRequestException("Cart with userid not found"));
        return ResponseEntity.ok().body(checkoutService.createOrderFromCartItem(userid, cart.getId()));
    }
}
