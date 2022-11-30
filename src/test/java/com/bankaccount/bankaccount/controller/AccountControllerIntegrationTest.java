package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.BankaccountApplication;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BankaccountApplication.class)
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void beforeEach() {
        accountRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        accountRepository.deleteAll();
    }

    @Test
    void shouldBeAbleToFetchAccountSummaryOfLoggedInUser() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account("abc", "abc@gmail.com", bCryptPasswordEncoder.encode("abc@123"));
        accountRepository.save(account);

        mockMvc.perform(get("/summary")
                        .with(httpBasic(account.getEmail(), "abc@123")))
                .andExpect(status().isOk());
    }

}
