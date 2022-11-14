package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.service.SignupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class SignupControllerTest {

    SignupService signupService;

    @BeforeEach
    public void beforeEach(){
        signupService = mock(SignupService.class);
    }

    @Test
    void shouldBeAbleToSignupCustomer() throws IOException, AlreadyExistingUser {
        SignupRequest signupRequest = new SignupRequest("latha", "latha@gmail.com", "Latha@123");
        SignupController signupController = new SignupController(signupService);

        signupController.signup(signupRequest);

        verify(signupService).save(signupRequest);

    }
}
