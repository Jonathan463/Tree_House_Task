package com.treehouseapp.service.serviceImpl;

import com.treehouseapp.exception.BadRequestException;
import com.treehouseapp.model.enums.PaymentMethod;
import com.treehouseapp.model.enums.TransactionStatus;
import com.treehouseapp.model.enums.TransactionType;
import com.treehouseapp.model.users.User;
import com.treehouseapp.model.wallets.Transaction;
import com.treehouseapp.model.wallets.Wallet;
import com.treehouseapp.repository.TransactionRepository;
import com.treehouseapp.repository.UserRepository;
import com.treehouseapp.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(WalletRepository walletRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }


    public String getTransactionRefence (long userId, TransactionType type, PaymentMethod paymentMethod){
        Optional<User> user = Optional.ofNullable(userRepository.findById(userId).
                orElseThrow(() -> new BadRequestException("the user cannot be null")));

        Optional<Wallet> wallet = walletRepository.findById(user.get().getWalletId().getId());

        //transaction creation
        Transaction transaction = new Transaction();
        if (type.equals(TransactionType.CREDIT)){
            transaction.setId(transaction.getId().replace("CHOMPT", "CHOMPFND"));
            transaction.setPaymentMethod(PaymentMethod.PAYSTACK);

        }
        if (type.equals(TransactionType.DEBIT) && paymentMethod.equals(PaymentMethod.PAYSTACK)){
            transaction.setId(transaction.getId().replace("CHOMPT", "CHOMPPSTK"));
            transaction.setPaymentMethod(PaymentMethod.PAYSTACK);
        }
        if (type.equals(TransactionType.DEBIT) && paymentMethod.equals(PaymentMethod.EWALLET)){
            transaction.setId(transaction.getId().replace("CHOMPT", "CHOMPWTH"));
            transaction.setPaymentMethod(PaymentMethod.EWALLET);
        }

        transaction.setWallet(wallet.get());
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setTransactionType(type);
        transaction.setUser(user.get());
        transactionRepository.save(transaction);
        return transaction.getId();
    }


}
