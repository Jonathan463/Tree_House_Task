package com.chompfooddeliveryapp.controller;

import com.chompfooddeliveryapp.dto.ProcessPaymentRequest;
import com.chompfooddeliveryapp.dto.VerifyTransactionDto;
import com.chompfooddeliveryapp.exception.BadRequestException;
import com.chompfooddeliveryapp.model.enums.OrderStatus;
import com.chompfooddeliveryapp.model.orders.Order;
import com.chompfooddeliveryapp.repository.OrderRepository;
import com.chompfooddeliveryapp.service.serviceInterfaces.PaymentService;
import com.chompfooddeliveryapp.service.serviceInterfaces.UserServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class ProcessPaymentController {

    private final PaymentService paymentservice;
    private final UserServiceInterface userService;
    private final OrderRepository orderRepository;

@Autowired
    public ProcessPaymentController(PaymentService paymentservice, UserServiceInterface userService, OrderRepository orderRepository) {
        this.paymentservice = paymentservice;

    this.userService = userService;
    this.orderRepository = orderRepository;
}

    @PostMapping("/process/")
    public ResponseEntity<?>processPayment(@RequestBody ProcessPaymentRequest processPaymentRequest){
    long userid = userService.getUserIDFromSecurityContext();
    Order order = orderRepository.findByUserIdAndStatus(userid, OrderStatus.PENDING).orElseThrow(()->
            new BadRequestException("You do not have a pending order")
            );
    Object output = paymentservice.processPayment(processPaymentRequest,userid, order.getId());
    return new ResponseEntity<>(output, HttpStatus.ACCEPTED);
    //todo: Makera's Call back function from PayStack will redirect to the verifyPayStackPayment endpoint
    }

    @PostMapping("/verifyPayment")
    public ResponseEntity<?> verifyPayStackPayment(@RequestBody VerifyTransactionDto transactionDto
             )
            throws JsonProcessingException {
        long userid = userService.getUserIDFromSecurityContext();
        Order order = orderRepository.findByUserIdAndStatus(userid, OrderStatus.PENDING).orElseThrow(()->
                new BadRequestException("You do not have a pending order")
        );
    return ResponseEntity.ok(paymentservice.verifyPayStackPayment(transactionDto,userid,order.getId()));


    }
}
