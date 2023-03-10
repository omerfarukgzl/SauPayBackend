package com.omer.Payment.model;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private BigDecimal amaount;
    private LocalDateTime localDateTime;


    //Lazy yükleme
    /*Bir üniversitede çok sayıda öğrenci varken, özellikle ihtiyaç olmadığında tüm öğrencilerini birlikte yüklemek verimli değildir
     ve bu gibi durumlarda öğrencilerin gerçekten ihtiyaç duyulduğu zaman yüklenmesini istediğinizi beyan edebilirsiniz. Buna tembel yükleme denir.*/

    //Eager yükleme
    /*Eager yükleme, bir nesnenin yüklenmesiyle ilişkili nesnelerin de yüklenmesini sağlar. Bu durumda, öğrencilerin yüklenmesiyle birlikte, öğrencilerin derslerini de yükler.*/

    // Biz burada Eager yükleme yaptık çünkü bir transaction oluşturulduğunda account bilgisi de olmalıdır.

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="card_id", nullable=false)// foreign key
    private Card card;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="commerce_company_id", nullable=false)// foreign key
    private CommerceCompany commerceCompany;

    public Transaction(Card card,BigDecimal amaount,LocalDateTime localDateTime,CommerceCompany commerceCompany)
    {
        this.id = "";
        this.card=card;
        this.amaount=amaount;
        this.localDateTime=localDateTime;
        this.commerceCompany=commerceCompany;

    }

    //!!!!!
    // Bir entity nesnesi  private Set<Transaction> transactions  = new HashSet<>() ; şeklinde bir set içerisinde tutuluyorsa
    // bu set içerisinde aynı id değerine sahip transaction nesnesi oluşmaması için bu metodları override etmemiz gerekir.


    // Equals metodunu override etmemizin sebebi
    // Transaction sınıfından oluşan verilerin karşılatırma yapılmasında id değerlerine göre yapılmasını sağlamaktır.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    // Hashcode metodunu override etmemizin sebebi
    // Transaction sınıfından oluşan verilerin bir veri yapısı kümesine eklenirken id parametresine bağlı olarak üretilen hashcode
    // değerlerine göre karşılaştırma yapıp eklemesini sağlamaktır.
    // Bu sayede veri yapısı kümesinde aynı id değerine sahip verilerin oluşmasını engellemiş oluruz.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}