package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>,CustomTransactionRepository { //==> Transaction modelinden ve string id'li bir repository oluşturduk.

    List<Transaction> findByCardId(String cardId); //==> CardId'ye göre Card a ait transaction'ları getirir.
    Optional<Transaction> findByToken(String token); //==> Token'a göre Card a ait transaction'ları getirir.

}
