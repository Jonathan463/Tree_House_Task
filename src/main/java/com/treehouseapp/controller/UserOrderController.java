package com.treehouseapp.controller;

import com.treehouseapp.dto.ResponseViewUserOrdersDTO;
import com.treehouseapp.dto.ShippingAddressDTO;
import com.treehouseapp.exception.BadRequestException;
import com.treehouseapp.model.enums.OrderStatus;
import com.treehouseapp.model.orders.Order;
import com.treehouseapp.repository.OrderRepository;
import com.treehouseapp.service.serviceInterfaces.CheckoutService;
import com.treehouseapp.service.serviceInterfaces.OrderService;
import com.treehouseapp.service.serviceInterfaces.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
