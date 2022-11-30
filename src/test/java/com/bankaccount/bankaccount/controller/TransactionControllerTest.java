package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.LoginPrincipalService;
import com.bankaccount.bankaccount.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    TransactionService transactionService;
    @Mock
    Principal principal;
    @InjectMocks
    TransactionController transactionController;

    @Test
    void shouldBeAbleToCreditAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);
        String email = "latha@gmail.com";
        when(principal.getName()).thenReturn(email);

        transactionController.credit(principal, amount);

        verify(transactionService).credit(amount, email);
    }

    @Test
    void shouldBeAbleToDebitAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);
        String email = "latha@gmail.com";
        when(principal.getName()).thenReturn(email);

        transactionController.debit(principal, amount);

        verify(transactionService).debit(amount, email);
    }

    @Test
    void shouldBeAbleToFetchStatement() {
        String email = "latha@gmail.com";
        when(principal.getName()).thenReturn(email);

        transactionController.statement(principal);

        verify(transactionService).statement(email);
    }
}
