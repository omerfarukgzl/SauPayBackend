package com.omer.Payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private String binNumber;
    private String cardHolderName;
    private String cardCvv;
    private String cardExpireDate;
    private String cardType;




    // Bir hesap birden fazla işlem yapabilir.
    @OneToMany(mappedBy = "card",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transaction> transactions  = new HashSet<>() ;

    // Bir kart bir Bankaya ait olabilir.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="bank_id", nullable=false)// foreign key
    private BankCompany bankCompany;

    // Bir kart bir hesaba bağlanabilir.
    //@JsonIgnore // Json formatında gösterilmesini istemediğimiz alanlar için kullanılır.
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Account account;


    public Card(String cardNumber, String binNumber, String cardHolderName, String cardCvv, String cardExpireDate, String cardType, Set<Transaction> transactions, Account account, BankCompany bankCompany) {
        this.cardNumber = cardNumber;
        this.binNumber = binNumber;
        this.cardHolderName = cardHolderName;
        this.cardCvv = cardCvv;
        this.cardExpireDate = cardExpireDate;
        this.cardType = cardType;
        this.transactions = transactions;
        this.account = account;
        this.bankCompany = bankCompany;
        //this.card_accounts = card_accounts;
    }


    // Bir kart birden fazla hesaba bağlanabilir.
/*
    @OneToMany(mappedBy = "card_account",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Account_Card> card_accounts  = new HashSet<>() ;
*/




}
