package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.Transaction;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;
    TransactionService transactionService;

    @BeforeEach
    public void beforeEach(){
        transactionRepository = mock(TransactionRepository.class);
        accountRepository = mock(AccountRepository.class);
        transactionService = new TransactionService(transactionRepository,accountRepository);
    }

    @Test
    void shouldBeAbleToSaveCreditedAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(4);
        Long accountId = 2L;
        Account account = new Account("latha","latha@gmail.com","latha@123");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        transactionService.credit(amount,accountId);

        verify(transactionRepository).save(any());
        verify(accountRepository).save(account);
    }
    @Test
    void shouldBeAbleToSaveDebitedAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(4);
        Long accountId = 2L;
        Account account = new Account("latha","latha@gmail.com","latha@123");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        transactionService.debit(amount,accountId);

        verify(transactionRepository).save(any());
        verify(accountRepository).save(account);
    }
}
