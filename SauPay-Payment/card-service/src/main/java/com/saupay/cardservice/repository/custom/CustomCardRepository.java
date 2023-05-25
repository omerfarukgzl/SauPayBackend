package com.saupay.cardservice.repository.custom;

import com.saupay.cardservice.dto.CardDto;
import com.saupay.cardservice.dto.CardJoinDto;
import com.saupay.cardservice.dto.CardJoinDtoList;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomCardRepository {
    Optional<CardJoinDto> findCardBankJoinByBinNumber(@Param("binNumber") Integer binNumber);

    Optional<CardJoinDtoList>  findCardBankJoinByUserId(@Param("userId") String  userId);
}
