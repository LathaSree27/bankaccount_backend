package com.bankaccount.bankaccount.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaction")
@Setter
@Getter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String type;

    private BigDecimal amount;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction(String type, BigDecimal amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.account = account;
    }
}
