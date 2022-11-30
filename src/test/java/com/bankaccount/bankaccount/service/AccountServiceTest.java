package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    AccountService accountService;

    @Test
    void shouldBeAbleToFetchAccountSummaryOfLoggedInUser() {
        String email = "abc@gmail.com";
        Account account = new Account("abc", email, "abc@123");
        when(accountRepository.findByEmail(email)).thenReturn(Optional.of(account));
        Map<String, String> expectedSummary = new HashMap<>();
        expectedSummary.put("Account Number", String.valueOf(account.getId()));
        expectedSummary.put("Account Holder Name", account.getName());
        expectedSummary.put("Balance", String.valueOf(account.getBalance()));

        Map<String, String> actualSummary = accountService.getSummary(email);

        assertThat(expectedSummary, is(actualSummary));
        verify(accountRepository).findByEmail(email);
    }
}
