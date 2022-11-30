package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.controller.response.TransactionStatement;
import com.bankaccount.bankaccount.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.security.Principal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    TransactionService transactionService;
    @Mock
    Principal principal;
    @InjectMocks
    TransactionController transactionController;

    @Test
    void shouldBeAbleToCreditAmountIntoLoggedInUserAccount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);

        transactionController.credit(principal, amount);

        verify(transactionService).credit(amount, principal.getName());
    }

    @Test
    void shouldBeAbleToDebitAmountFromLoggedInUserAccount() throws Exception {
        BigDecimal amount = BigDecimal.valueOf(2);

        transactionController.debit(principal, amount);

        verify(transactionService).debit(amount, principal.getName());
    }

    @Test
    void shouldBeAbleToFetchStatementOfTheLoggedInUser() {
        TransactionStatement expectedTransactionStatement = TransactionStatement.builder().build();
        when(transactionService.statement(principal.getName())).thenReturn(expectedTransactionStatement);
        TransactionStatement actualTransactionResponse = transactionController.statement(principal);

        verify(transactionService).statement(principal.getName());
        assertThat(actualTransactionResponse, is(expectedTransactionStatement));
    }
}
