package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignupServiceTest {
    @Mock
    AccountRepository accountRepository;
    @InjectMocks
    SignupService signupService;

    @Test
    void shouldBeAbleToSaveAccountDetailsWhenNewUserTriesToSignup() throws AlreadyExistingUser {
        SignupRequest signupRequest = new SignupRequest("latha", "latha@gmail.com", "Latha@123");

        signupService.save(signupRequest);

        verify(accountRepository).save(any());
    }

    @Test
    void shouldNotBeAbleToSaveAccountDetailsWhenExistingUserTriesToSignup() {
        SignupRequest signupRequest = new SignupRequest("latha", "latha@gmail.com", "Latha@123");
        Account account = new Account(signupRequest);
        when(accountRepository.findByEmail(signupRequest.getEmail())).thenReturn(Optional.of(account));

        assertThrows(AlreadyExistingUser.class, () -> signupService.save(signupRequest));
    }
}
