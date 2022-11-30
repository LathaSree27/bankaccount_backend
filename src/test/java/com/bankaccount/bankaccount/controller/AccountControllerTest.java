package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    AccountService accountService;
    @Mock
    Principal principal;

    @InjectMocks
    AccountController accountController;

    @Test
    void shouldBeAbleToFetchAccountDetails() {
        accountController.summary(principal);

        verify(accountService).getSummary(any());
    }
}
