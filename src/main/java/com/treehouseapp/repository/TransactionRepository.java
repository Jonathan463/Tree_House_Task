package com.treehouseapp.repository;

import com.treehouseapp.model.wallets.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
