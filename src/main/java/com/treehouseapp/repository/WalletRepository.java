package com.treehouseapp.repository;

import com.treehouseapp.model.wallets.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, String> {

//    @Query("select c from Wallet c where c.user.id = ?1")
//    Wallet findByUserId(Long user_id);
//    Boolean findByUserIdIsNull(long userid);
//    Optional<Wallet> findWalletByUserId(long userid);
}
