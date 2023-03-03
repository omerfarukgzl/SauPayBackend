package com.omer.Payment.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Customer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerTC;
    private String customerPassword;

    // Bir müşterinin yanında bir hesabı olur.
    // Bir hesap birden fazla müşteriye ait olamaz.
    // Bu yüzden OneToOne kullanıldı.
    // mappedBy="customer" : customer nesnesi Account nesnesindeki customer nesnesine karşılık gelir.
    // fetch = FetchType.EAGER : Müşteri nesnesi çağırıldığında, müşteri bilgileri ve hesap bilgileri çekilir.
    // cascade = CascadeType.ALL : Müşteri nesnesi silindiğinde, hesap nesnesi de silinir.
    // private Account account;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="account_id", nullable=false)// foreign key
    private Account account;





}
