package com.saupay.cardservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;
    private String bankName;
    private Integer bankCode;

    public Bank(String bankName, Integer bankCode, Set<Card> cards) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        //this.cards = cards;

    }

/*    @OneToMany(mappedBy = "bank",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Card> cards  = new HashSet<>() ;*/










}
