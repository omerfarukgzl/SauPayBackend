package com.saupay.cardservice.repository;


import com.saupay.cardservice.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, String> {

    Optional<Bank> findByBankCode(Integer i);
}
