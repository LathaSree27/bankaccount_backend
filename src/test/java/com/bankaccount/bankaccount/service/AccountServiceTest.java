package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
        Map<String, String> expectedSummary = new HashMap<>();
        expectedSummary.put("Account Number", String.valueOf(account.getId()));
        expectedSummary.put("Account Holder Name", account.getName());
        expectedSummary.put("Balance", String.valueOf(account.getBalance()));
        AccountService accountService = new AccountService(accountRepository);

        Map<String, String> actualSummary = accountService.getSummary(email);

        assertThat(expectedSummary,is(actualSummary));
        verify(accountRepository).findByEmail(email);
    }
}
