package com.treehouseapp.service.serviceInterfaces;

import com.treehouseapp.dto.ProcessPaymentRequest;
import com.treehouseapp.payload.VerificationResponse;
import com.treehouseapp.dto.VerifyTransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface PaymentService {

    Object processPayment(ProcessPaymentRequest request, Long userId, Long orderId);

    VerificationResponse verifyPayStackPayment(VerifyTransactionDto transactionDto, Long userId, Long orderId)
            throws JsonProcessingException;

    void checkLoginStatus(Long userId);
}
