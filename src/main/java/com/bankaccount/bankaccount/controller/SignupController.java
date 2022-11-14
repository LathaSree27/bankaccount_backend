package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.service.SignupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping
@AllArgsConstructor
public class SignupController {

    SignupService signupService;

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void signup(@RequestBody SignupRequest signupRequest) throws IOException, AlreadyExistingUser {
        signupService.save(signupRequest);
    }
}
