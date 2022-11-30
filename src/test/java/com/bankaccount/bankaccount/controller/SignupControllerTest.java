package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.service.SignupService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SignupControllerTest {
    @Mock
    SignupService signupService;
    @InjectMocks
    SignupController signupController;

    @Test
    void shouldBeAbleToSignupCustomer() throws IOException, AlreadyExistingUser {
        SignupRequest signupRequest = new SignupRequest("abc", "abc@gmail.com", "abc@123");

        signupController.signup(signupRequest);

        verify(signupService).save(signupRequest);
    }
}
