package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.LoginPrincipalService;
import com.bankaccount.bankaccount.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    TransactionService transactionService;
    LoginPrincipalService loginPrincipalService;
    Principal principal;

    @BeforeEach
    public void beforeEach() {
        transactionService = mock(TransactionService.class);
        principal = mock(Principal.class);
    }

    @Test
    void shouldBeAbleToCreditAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);
        TransactionController transactionController = new TransactionController(transactionService);
        String email = "latha@gmail.com";
        when(principal.getName()).thenReturn(email);

        transactionController.credit(principal, amount);

        verify(transactionService).credit(amount, email);
    }

    @Test
    void shouldBeAbleToDebitAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);
        TransactionController transactionController = new TransactionController(transactionService);
        String email = "latha@gmail.com";
        when(principal.getName()).thenReturn(email);

        transactionController.debit(principal, amount);

        verify(transactionService).debit(amount, email);
    }
}
