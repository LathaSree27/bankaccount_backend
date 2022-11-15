package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.controller.response.TransactionResponse;
import com.bankaccount.bankaccount.controller.response.TransactionStatement;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.Transaction;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    public void credit(BigDecimal amount, String email) {
        Account account = accountRepository.findByEmail(email).get();
        Transaction transaction = new Transaction("CREDIT", amount, account);
        transactionRepository.save(transaction);
        System.out.println(account.getBalance());
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void debit(BigDecimal amount, String email) {
        Account account = accountRepository.findByEmail(email).get();
        Transaction transaction = new Transaction("DEBIT", amount, account);
        transactionRepository.save(transaction);
        System.out.println(account.getBalance());
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    public TransactionStatement statement(String email) {
        Account account = accountRepository.findByEmail(email).get();
        long accountId = account.getId();
        List<Transaction> transactions = transactionRepository.findByAccount_id(accountId);
        List<TransactionResponse> transactionResponses = getTransactionResponses(transactions);
        TransactionStatement transactionStatement = new TransactionStatement(accountId,account.getName(),transactionResponses,account.getBalance());

        return transactionStatement;
    }

    private static List<TransactionResponse> getTransactionResponses(List<Transaction> transactions) {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for(Transaction transaction : transactions){
            transactionResponses.add(new TransactionResponse(transaction.getType(),transaction.getAmount()));
        }
        return transactionResponses;
    }
}
