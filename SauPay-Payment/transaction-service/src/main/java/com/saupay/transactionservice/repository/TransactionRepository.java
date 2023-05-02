package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> { //==> Transaction modelinden ve string id'li bir repository oluşturduk.

    List<Transaction> findByCardId(String cardId); //==> CardId'ye göre Card a ait transaction'ları getirir.

    // Inner Join ile Transaction ve Merchant tablolarını birleştirip, cardId'ye göre transaction_merchantDto 'ları getirir.

    List<Transaction_MerchantDto> findTransactionsMerchantById(String cardId);

}
