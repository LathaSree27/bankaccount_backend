package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.BankaccountApplication;
import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BankaccountApplication.class)
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SignupControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void beforeEach() {
        accountRepository.deleteAll();
    }

    @AfterEach
    public void afterEach() {
        accountRepository.deleteAll();
    }

    @Test
    void shouldBeAbleToSignUp() throws Exception {
        String uri = "/signup";
        SignupRequest signupRequest = new SignupRequest("abc", "abc@gmail.com", "abc@123");
        String requestJson = new ObjectMapper().writeValueAsString(signupRequest);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(status().isCreated());

    }

    @Test
    void shouldThrowErrorWhenExistingEmailIdIsProvidedForSignup() throws Exception {

        String uri = "/signup";
        Account account = new Account("abc", "abc@gmail.com", "abc@123");
        accountRepository.save(account);
        SignupRequest signupRequest = new SignupRequest("abcd", "abc@gmail.com", "abc@123");
        String requestJson = new ObjectMapper().writeValueAsString(signupRequest);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(status().isBadRequest());
    }
}
