package com.saupay.cardservice.repository;

import com.saupay.cardservice.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankCardRepository extends JpaRepository<BankCard, String> {

    Optional<BankCard> findByBinNumber(Integer i);
}
