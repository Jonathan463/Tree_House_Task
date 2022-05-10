package com.treehouseapp.payload;

import com.treehouseapp.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletPayload {
    private String  walletId;
    private long user_id;
    private Currency currency = Currency.NGN;
    private long accountBalance;
    private long amountCredited;
    private String transactionStatus;
    private String gatewayResponse;
}
