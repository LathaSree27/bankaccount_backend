package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.controller.response.TransactionResponse;
import com.bankaccount.bankaccount.controller.response.TransactionStatement;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.Transaction;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;
    TransactionService transactionService;

    TransactionStatement transactionStatement;

    TransactionResponse transactionResponse;

    @BeforeEach
    public void beforeEach() {
        transactionRepository = mock(TransactionRepository.class);
        accountRepository = mock(AccountRepository.class);
        transactionResponse = mock(TransactionResponse.class);
        transactionStatement = mock(TransactionStatement.class);
        transactionService = new TransactionService(transactionRepository, accountRepository);
    }

    @Test
    void shouldBeAbleToSaveCreditedAmount() {
        BigDecimal amount = BigDecimal.valueOf(4);
        String email = "latha@gmail.com";
        Account account = new Account("latha", email, "latha@123");
        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));

        transactionService.credit(amount, email);

        assertEquals(new BigDecimal(4), account.getBalance());
        verify(transactionRepository).save(any());
        verify(accountRepository).save(account);
    }

    @Test
    void shouldBeAbleToSaveDebitedAmount() {
        BigDecimal amount = BigDecimal.valueOf(4);
        String email = "latha@gmail.com";
        Account account = new Account("latha", email, "latha@123");
        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));

        transactionService.debit(amount, email);

        assertEquals(new BigDecimal(-4), account.getBalance());
        verify(transactionRepository).save(any());
        verify(accountRepository).save(account);
    }

    @Test
    void shouldBeAbleToFetchTransactions() {
        String email = "latha@gmail.com";
        Account account = new Account("latha", email, "latha@123");
        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));
        long accountId = account.getId();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("CREDIT", new BigDecimal(10), account));
        transactions.add(new Transaction("DEBIT", new BigDecimal(5), account));
        when(transactionRepository.findByAccount_id(accountId)).thenReturn(transactions);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionResponses.add(TransactionResponse.builder().type(transaction.getType()).amount(transaction.getAmount()).build());
        }
        TransactionStatement transactionStatement1 = TransactionStatement.builder().accountId(accountId).accountHolderName(account.getName()).transactions(transactionResponses).balance(account.getBalance()).build();

        TransactionStatement statement = transactionService.statement(email);

        assertThat(transactionStatement1, is(statement));
        verify(transactionRepository).findByAccount_id(accountId);
    }
}
