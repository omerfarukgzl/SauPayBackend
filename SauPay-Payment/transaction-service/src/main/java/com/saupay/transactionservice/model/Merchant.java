package com.saupay.transactionservice.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Merchant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Merchant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    private String merchantName;
    private String merchantCode;

}

