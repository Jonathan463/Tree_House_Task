package com.chompfooddeliveryapp.controller;

import com.chompfooddeliveryapp.dto.ResponseViewUserOrdersDTO;
import com.chompfooddeliveryapp.dto.ShippingAddressDTO;
import com.chompfooddeliveryapp.exception.BadRequestException;
import com.chompfooddeliveryapp.model.enums.OrderStatus;
import com.chompfooddeliveryapp.model.orders.Order;
import com.chompfooddeliveryapp.repository.OrderRepository;
import com.chompfooddeliveryapp.service.serviceInterfaces.CheckoutService;
import com.chompfooddeliveryapp.service.serviceInterfaces.OrderService;
import com.chompfooddeliveryapp.service.serviceInterfaces.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserOrderController {

    private final OrderService orderService;
    private final CheckoutService checkoutService;
    private final UserServiceInterface userService;
    private final OrderRepository orderRepository;
    @Autowired
    public UserOrderController(OrderService orderService,
                               CheckoutService checkoutService,
                               UserServiceInterface userService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.checkoutService = checkoutService;
        this.userService = userService;

        this.orderRepository = orderRepository;
    }


    @GetMapping("/view_order_details")
    public ResponseEntity<?> viewOrderDetails( ){
        long userid = userService.getUserIDFromSecurityContext();
        Order order = orderRepository.findByUserIdAndStatus(userid, OrderStatus.PENDING).orElseThrow(()->
                new BadRequestException("You do not have a pending order"));
        return new ResponseEntity<>(orderService.getOrderDetails(userid, order.getId()), HttpStatus.OK);
    }

    @PostMapping("/shipping-address")
    public ResponseEntity<List<String>> saveShippingAddress(
                                                            @RequestBody ShippingAddressDTO shippingAddress) {
        var responseText = checkoutService.saveShippingAddress(userService.getUserIDFromSecurityContext(), shippingAddress);
        return new ResponseEntity<>(responseText, HttpStatus.OK);
    }

    @GetMapping("/view-all-orders")
    public ResponseEntity<ResponseViewUserOrdersDTO> viewAllOrders( ) {
        var allUserOrders = orderService.getAllOrdersByUserId(userService.getUserIDFromSecurityContext());
        return new ResponseEntity<>(allUserOrders, HttpStatus.OK);
    }

}
