package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {

    Optional<Merchant> findByMerchantCode(String merchantCode);
}
