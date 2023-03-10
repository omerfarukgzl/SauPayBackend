package com.omer.Payment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    // UUID, 128 bitlik bir sayıdır. 32 karakter uzunluğunda bir hexadecimal sayıdır.
    // UUID, 32 karakterden oluşan 5 parçadan oluşur. 8-4-4-4-12 şeklinde.
    // Veritabanında Tahmin Edilemez bir ID oluşturmak için UUID kullanıldı.
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    // Hesabın oluşturulma tarihi
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private LocalDateTime creationDate;

/*    @OneToOne(mappedBy="account",cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Customer customer;*/

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Card> cards = new HashSet<>();

/*    // Bir hesap birden fazla kart ile ilişkilendirilebilir.
    @OneToMany(mappedBy = "account_card",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Account_Card> account_cards  = new HashSet<>() ;*/

    public Account(LocalDateTime creationDate, Set<Card> cards) {
        this.id = "";
        this.creationDate = creationDate;
        this.cards = cards;
    }


    // Bir hesap birden fazla işlem yapabilir.
/*
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Transaction> transactions  = new HashSet<>() ;

*/




















    //LAZY: Hesap nesnesi çağırıldığında, hesap bilgileri çekilir. Transaction bilgileri çekilmez.
    //EAGER: Hesap nesnesi çağırıldığında, hesap bilgileri ve Transaction bilgileri çekilir.

    // Biz hesap nesnesini çağırdığımızda hesap bilgilerini ve Transaction bilgilerini çekmek istiyoruz.
    // Bu yüzden EAGER kullanıldı.







    /*ManyToMany’yi olabildiğince kullanmamanızı, onun yerine gerekirse bir tablo daha açıp onun üzerinde her ikisini de ManyToOne olarak ona kaydetmenizi tavsiye ederim
    ManyToMany’de performans sorundur, karmaşık çoktur, veri bütünlüğü sorunları oluşabilir ve zaman alıcıdır.
    Bu ilk etapta sorunsuz gibi gözükse de sonrasında başınızı ağrıtır*/
/*
    @ManyToMany
    @JoinTable(name = "account_card",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    private Set<Card> cards = new HashSet<>();
*/






}
