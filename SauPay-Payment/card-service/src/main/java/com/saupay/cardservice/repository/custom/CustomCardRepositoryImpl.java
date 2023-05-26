package com.saupay.cardservice.repository.custom;

import com.saupay.cardservice.dto.CardJoinDto;
import com.saupay.cardservice.dto.CardJoinDtoList;
import com.saupay.cardservice.dto.converter.CardDtoJoinConverter;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomCardRepositoryImpl implements CustomCardRepository {

    private final CardDtoJoinConverter cardDtoConverter;
    private final SessionFactory sessionFactory;

    public CustomCardRepositoryImpl(CardDtoJoinConverter cardDtoConverter, SessionFactory sessionFactory) {
        this.cardDtoConverter = cardDtoConverter;
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Optional<CardJoinDtoList> findCardBankJoinByUserId (String userId) {
        var session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT c.card_number,c.bin_number ,c.card_holder_name, c.card_cvv, c.card_expire_date,c.card_type, b.bank_name FROM card c JOIN bank b ON c.bank_code = b.bank_code WHERE c.user_id ="+ "'" + userId + "'");
        /*List<Transaction_MerchantDto> list = query.list();
        return list;*/
        List<Object[]> resultList = query.getResultList();

// Query sonuçlarını DTO nesnesine dönüştürmek için bir listesi oluşturun.

        List<CardJoinDto> dtoList = new ArrayList<>();
        CardJoinDtoList cardDtoList = new CardJoinDtoList();
        for (Object[] result : resultList) {
            CardJoinDto dto = cardDtoConverter.convert(result);
            dtoList.add(dto);
            System.out.println("find Card : " + dto.getCardNumber() + " " + dto.getBinNumber() + " " + dto.getCardHolderName() + " " + dto.getCardCvv() + " " + dto.getCardExpireDate() + " " + dto.getCardType() + " " + dto.getBankName());
        }
        cardDtoList.setCards(dtoList);

        return Optional.of(cardDtoList);
    }

    @Override
    public Optional<CardJoinDto> findCardBankJoinByBinNumber(Integer binNumber) {
        var session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT c.card_number,c.bin_number ,c.card_holder_name, c.card_cvv, c.card_expire_date,c.card_type, b.bank_name FROM card c JOIN bank b ON c.bank_code = b.bank_code WHERE c.bin_number ="+ "'" + binNumber + "'");
        /*List<Transaction_MerchantDto> list = query.list();
        return list;*/
        List<Object[]> resultList = query.getResultList();

// Query sonuçlarını DTO nesnesine dönüştürmek için bir listesi oluşturun.

        List<CardJoinDto> dtoList = new ArrayList<>();
        CardJoinDto cardDto = new CardJoinDto();
        for (Object[] result : resultList) {
            CardJoinDto dto = cardDtoConverter.convert(result);
            dtoList.add(dto);
        }
        cardDto.setCardNumber(dtoList.get(0).getCardNumber());
        cardDto.setBinNumber(dtoList.get(0).getBinNumber());
        cardDto.setCardHolderName(dtoList.get(0).getCardHolderName());
        cardDto.setCardCvv(dtoList.get(0).getCardCvv());
        cardDto.setCardExpireDate(dtoList.get(0).getCardExpireDate());
        cardDto.setCardType(dtoList.get(0).getCardType());
        cardDto.setBankName(dtoList.get(0).getBankName());

        return Optional.of(cardDto);
    }
}
