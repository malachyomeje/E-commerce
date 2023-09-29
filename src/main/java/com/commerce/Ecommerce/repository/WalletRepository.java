package com.commerce.Ecommerce.repository;

import com.commerce.Ecommerce.model.Token;
import com.commerce.Ecommerce.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {


}
