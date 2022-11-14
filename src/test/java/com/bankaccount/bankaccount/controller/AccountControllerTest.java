package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.security.Principal;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    AccountService accountService;
    Principal principal;

    @BeforeEach
    public void beforeEach(){
        accountService = mock(AccountService.class);
        principal = mock(Principal.class);

    }

    @Test
    void shouldBeAbleToFetchAccountDetails() {
        AccountController accountController = new AccountController(accountService);

        accountController.summary(principal);

        verify(accountService).getSummary(any());
    }
}
