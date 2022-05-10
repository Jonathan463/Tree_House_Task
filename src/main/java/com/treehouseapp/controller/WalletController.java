package com.treehouseapp.controller;

import com.treehouseapp.payload.PayStackResponse;
import com.treehouseapp.dto.VerifyTransactionDto;
import com.treehouseapp.dto.PayStackRequest;
import com.treehouseapp.payload.WalletPayload;
import com.treehouseapp.service.serviceImpl.PaystackServiceImpl;
import com.treehouseapp.service.serviceImpl.WalletServiceImpl;
import com.treehouseapp.service.serviceInterfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.treehouseapp.model.enums.TransactionType.*;

@RestController
@RequestMapping("/user/wallet")
public class WalletController {


    private final WalletServiceImpl walletService;
    private final PaystackServiceImpl paystackService;
    private final UserServiceInterface userService;

    @Autowired
    public WalletController(WalletServiceImpl walletService, PaystackServiceImpl paystackService, UserServiceInterface userService) {

        this.walletService = walletService;
        this.paystackService = paystackService;
        this.userService = userService;
    }


    @PostMapping("/fundwallet")
    public Object initializeWalletTransaction(@RequestBody PayStackRequest payStackRequestDto)
                                              {

        return paystackService.initializePaystackTransaction(payStackRequestDto, userService.getUserIDFromSecurityContext(), CREDIT);

    }

    @GetMapping ("/verifytransaction")
    public ResponseEntity<?> verifyWalletTransaction(@RequestBody VerifyTransactionDto verifyTransactionDto) throws Exception {

        PayStackResponse responseDto = paystackService.verifyPaystackTransaction(verifyTransactionDto);

       WalletPayload walletPayload =  walletService.fundUsersWallet(verifyTransactionDto.getTransactionReference(),
               responseDto.getStatus(), responseDto.getData().get("status").toString(),
               responseDto.getData().get("amount").toString());
       walletPayload.setGatewayResponse(responseDto.getData().get("gateway_response").toString());

       return new ResponseEntity<>(walletPayload, HttpStatus.OK);

    }

    @GetMapping("/walletBalance")
    public String checkWalletBalance() {

        return walletService.getWalletBalance(userService.getUserIDFromSecurityContext());
    }




}
