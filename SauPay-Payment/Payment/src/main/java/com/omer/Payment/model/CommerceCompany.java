package com.omer.Payment.model;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CommerceCompany")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommerceCompany {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID",strategy="org.hibernate.id.UUIDGenerator")
    private String id;

    private String companyName;
    private String companyCode;

    public CommerceCompany(String companyName, String companyCode, Set<Transaction> transaction) {
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.transactions = transaction;
    }


    @OneToMany(mappedBy = "commerceCompany",fetch = FetchType.EAGER, cascade = CascadeType.ALL) // ==> mappedBy="account" : account nesnesi Transaction nesnesindeki account nesnesine karşılık gelir.
    private Set<Transaction> transactions  = new HashSet<>() ;



}
