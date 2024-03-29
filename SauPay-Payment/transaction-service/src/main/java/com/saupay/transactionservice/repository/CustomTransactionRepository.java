package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.response.TreeDSecureResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomTransactionRepository {
    Transaction_MerchantsDto findTransactionsMerchantById(@Param("cardId") String cardId);
    Transaction_MerchantDto findTransactionsMerchantByToken(@Param("token") String token);

    TreeDSecureResponse findTreeDSecureResponseByToken(@Param("token") String token);

}
