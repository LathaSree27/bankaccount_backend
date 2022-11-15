package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.Transaction;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
