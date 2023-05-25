package com.saupay.cardservice.repository;

import com.saupay.cardservice.model.Card;
import com.saupay.cardservice.repository.custom.CustomCardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> , CustomCardRepository {

    List<Card> findByUserId(String userId);


    Optional<Card> findByBinNumber(Integer i);
}
