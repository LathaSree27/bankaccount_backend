package com.bankaccount.bankaccount.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransactionStatement {
    private long accountId ;
    private String accountHolderName;
    List<TransactionResponse> transactions = new ArrayList<>(0);
    private BigDecimal balance;

    public TransactionStatement(long accountId, String accountHolderName, List<TransactionResponse> transactions, BigDecimal balance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.transactions = transactions;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionStatement that = (TransactionStatement) o;
        return accountId == that.accountId && Objects.equals(accountHolderName, that.accountHolderName) && Objects.equals(transactions, that.transactions) && Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, accountHolderName, transactions, balance);
    }
}
