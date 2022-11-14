package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SignupServiceTest {

    AccountRepository accountRepository;
    SignupService signupService;

    @BeforeEach
    public void beforeEach(){
        accountRepository = mock(AccountRepository.class);
        signupService = new SignupService (accountRepository);
    }

    @Test
    void shouldBeAbleToSaveAccountDetailsWhenNewUserTriesToSignup() throws AlreadyExistingUser {
        SignupRequest signupRequest = new SignupRequest("latha", "latha@gmail.com", "Latha@123");
        Account account = new Account(signupRequest);

        signupService.save(signupRequest);

        verify(accountRepository).save(any());
    }

    @Test
    void shouldNotBeAbleToSaveAccountDetailsWhenExistingUserTriesToSignup() {
        SignupRequest signupRequest = new SignupRequest("latha", "latha@gmail.com", "Latha@123");
        Account account = new Account(signupRequest);
        when(accountRepository.findByEmail(signupRequest.getEmail())).thenReturn(Optional.of(account));

        assertThrows(AlreadyExistingUser.class ,()->  signupService.save(signupRequest));


    }

}
