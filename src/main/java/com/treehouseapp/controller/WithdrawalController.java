package com.treehouseapp.controller;


import com.treehouseapp.dto.WithdrawalDto;
import com.treehouseapp.dto.WithdrawalRequest;
import com.treehouseapp.service.serviceImpl.WalletWithdrawServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class WithdrawalController {

    private final WalletWithdrawServiceImpl walletWithdrawServiceImpl;

    public WithdrawalController(WalletWithdrawServiceImpl walletWithdrawServiceImpl) {
        this.walletWithdrawServiceImpl = walletWithdrawServiceImpl;
    }

    @PostMapping("/withdraw")
    public ResponseEntity<WithdrawalDto> walletWithdrawal(@RequestBody WithdrawalRequest withdrawalRequest) {
        return walletWithdrawServiceImpl.walletWithdraw(withdrawalRequest);

    }
}
