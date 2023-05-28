package com.saupay.transactionservice.repository;

import com.saupay.transactionservice.dto.Transaction_MerchantDto;
import com.saupay.transactionservice.dto.Transaction_MerchantsDto;
import com.saupay.transactionservice.dto.converter.Transaction_MerchantDtoConverter;
import com.saupay.transactionservice.response.TreeDSecureResponse;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        Query query = session.createNativeQuery("SELECT m.merchant_name, t.amount, t.local_date_time " +
                "FROM transaction t " +
                "JOIN merchant m ON t.merchant_id = m.id " +
                "WHERE t.card_id ="+ "'" + cardId + "'" +
                "AND t.status = true");
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
        session.close();
        return transaction_merchantsDto;
    }

    @Override
    public Transaction_MerchantDto findTransactionsMerchantByToken(String token) {
        var session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT m.merchant_name, t.amount, t.local_date_time FROM transaction t JOIN merchant m ON t.merchant_id = m.id WHERE t.token =" + "'" + token + "'");
        /*List<Transaction_MerchantDto> list = query.list();
        return list;*/
        List<Object[]> resultList = query.getResultList();

// Query sonuçlarını DTO nesnesine dönüştürmek için bir listesi oluşturun.

        List<Transaction_MerchantDto> dtoList = new ArrayList<>();
        Transaction_MerchantDto transaction_merchantDto = new Transaction_MerchantDto();
        for (Object[] result : resultList) {
            Transaction_MerchantDto dto = transactionMerchantDtoConverter.convert(result);
            dtoList.add(dto);
        }
        transaction_merchantDto.setMerchantName(dtoList.get(0).getMerchantName());
        transaction_merchantDto.setAmount(dtoList.get(0).getAmount());
        transaction_merchantDto.setLocalDateTime(dtoList.get(0).getLocalDateTime());
        session.close();
        return transaction_merchantDto;
    }


    @Override
    public TreeDSecureResponse findTreeDSecureResponseByToken(String token) {
        var session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT m.merchant_name, t.amount, t.local_date_time, c.card_number, u.user_phone" +
                " FROM transaction t " +
                "JOIN merchant m ON t.merchant_id = m.id " +
                "JOIN card c ON t.card_id = c.id " +
                "JOIN userdb u ON t.user_id = u.id " +
                "WHERE t.token =" + "'" + token + "'");

        List<Object[]> rows = query.getResultList();
        List<TreeDSecureResponse> resultList = new ArrayList<>();
        for (Object[] row : rows) {
            TreeDSecureResponse result = new TreeDSecureResponse();
            result.setMerchantName((String) row[0]);
            result.setAmount((BigDecimal) row[1]);
            result.setDate((Timestamp) row[2]);
            result.setCardNumber((String) row[3]);
            result.setUserPhone((String) row[4]);

            resultList.add(result);
        }

        System.out.println(resultList.get(0).getMerchantName() + " " + resultList.get(0).getAmount() + " " + resultList.get(0).getDate() + " " + resultList.get(0).getCardNumber() + " " + resultList.get(0).getUserPhone());
        return resultList.get(0);
    }
}
