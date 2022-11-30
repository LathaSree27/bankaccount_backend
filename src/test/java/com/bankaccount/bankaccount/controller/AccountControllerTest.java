package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    AccountService accountService;
    @Mock
    Principal principal;

    @InjectMocks
    AccountController accountController;

    @Test
    void shouldBeAbleToFetchAccountSummaryOfLoggedInUser() {
        Map<String, String> expectedSummaryResponse = new HashMap<>();
        when(accountService.getSummary(principal.getName())).thenReturn(expectedSummaryResponse);

        Map<String, String> actualSummaryResponse = accountController.summary(principal);

        verify(accountService).getSummary(principal.getName());
        assertThat(actualSummaryResponse, is(expectedSummaryResponse));
    }
}
