package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.converter.Transaction_MerchantDtoConverter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomTransactionRepositoryImpl implements CustomTransactionRepository{

    private final Transaction_MerchantDtoConverter transactionMerchantDtoConverter;
    private final SessionFactory sessionFactory;

    public CustomTransactionRepositoryImpl(Transaction_MerchantDtoConverter transactionMerchantDtoConverter, SessionFactory sessionFactory) {
        this.transactionMerchantDtoConverter = transactionMerchantDtoConverter;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Transaction_MerchantsDto findTransactionsMerchantById(String cardId) {
        var session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT m.merchant_name, t.amount, t.local_date_time FROM transaction t JOIN merchant m ON t.merchant_id = m.id WHERE t.card_id ="+ "'" + cardId + "'");
        /*List<Transaction_MerchantDto> list = query.list();
        return list;*/
        List<Object[]> resultList = query.getResultList();

// Query sonuçlarını DTO nesnesine dönüştürmek için bir listesi oluşturun.
        List<Transaction_MerchantDto> dtoList = new ArrayList<>();
        Transaction_MerchantsDto transaction_merchantsDto = new Transaction_MerchantsDto();
        for (Object[] result : resultList) {
            Transaction_MerchantDto dto = transactionMerchantDtoConverter.convert(result);
            dtoList.add(dto);
        }
        transaction_merchantsDto.setTransactions(dtoList);
        return transaction_merchantsDto;
    }
}
