package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.BankaccountApplication;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BankaccountApplication.class)
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void beforeEach() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();

    }

    @AfterEach
    public void afterEach() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();

    }

    @Test
    void shouldBeAbleToCreditAmountIntoLoggedInUserAccount() throws Exception {
        String uri = "/transaction/credit";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account("abc", "abc@gmail.com", bCryptPasswordEncoder.encode("abc@12345"));
        accountRepository.save(account);

        mockMvc.perform(post(uri)
                        .with(httpBasic(account.getEmail(), "abc@12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("amount", String.valueOf(new BigDecimal(4))))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldBeAbleToDebitAmountFromLoggedInUserAccount() throws Exception {
        String uri = "/transaction/debit";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account("abc", "abc@gmail.com", bCryptPasswordEncoder.encode("abc@12345"));
        accountRepository.save(account);

        mockMvc.perform(post(uri)
                        .with(httpBasic(account.getEmail(), "abc@12345"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("amount", String.valueOf(new BigDecimal(4))))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldBeAbleToFetchTransactionStatement() throws Exception {
        String uri = "/transaction/statement";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account("abc", "abc@gmail.com", bCryptPasswordEncoder.encode("abc@12345"));
        accountRepository.save(account);

        mockMvc.perform(get(uri)
                        .with(httpBasic(account.getEmail(), "abc@12345")))
                .andExpect(status().isOk());
    }
}
