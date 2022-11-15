package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AccountServiceTest {

    AccountRepository accountRepository;

    @BeforeEach
    public void beforeEach() {
        accountRepository = mock(AccountRepository.class);
    }

    @Test
    void shouldBeAbleToGetAccountDetails() {
        String email = "latha@gmail.com";
        Account account = new Account("latha", email, "Latha@123");
        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));
        AccountService accountService = new AccountService(accountRepository);

        accountService.getSummary(email);

        verify(accountRepository).findByEmail(email);
    }
}
