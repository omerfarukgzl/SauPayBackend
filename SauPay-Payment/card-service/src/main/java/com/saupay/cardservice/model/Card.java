package com.saupay.cardservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@Table(name = "Card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;
    private String cardNumber;
    private Integer binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
    private String cardType;
    private String userId;

    private Integer bankCode;
    private String bankCardId;

/*
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="bank_id", nullable=false)// foreign key
    private Bank bank;
*/

    public Card(String cardNumber, Integer binNumber, String cardHolderName, String cardCvv, String cardExpireDate, String cardType, String userId, Integer bankCode, String bankCardId) {
        this.cardNumber = cardNumber;
        this.binNumber = binNumber;
        this.cardHolderName = cardHolderName;
        this.cardCvv = cardCvv;
        this.cardExpireDate = cardExpireDate;
        this.cardType = cardType;
        this.userId = userId;
        this.bankCode = bankCode;
        this.bankCardId = bankCardId;
        //this.card_accounts = card_accounts;
    }

}
