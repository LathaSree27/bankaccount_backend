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
    public void beforeEach(){
        transactionService = mock(TransactionService.class);
        loginPrincipalService =  mock(LoginPrincipalService.class);
        principal = mock(Principal.class);
    }

    @Test
    void shouldBeAbleToCreditAmount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);
        long accountId = 23;
        TransactionController transactionController = new TransactionController(transactionService,loginPrincipalService);
        when(principal.getName()).thenReturn("latha@gmail.com");
        when(loginPrincipalService.getAccountId("latha@gmail.com")).thenReturn(accountId);

        transactionController.credit(principal,amount);

        verify(transactionService).credit(amount, accountId);
    }
}
