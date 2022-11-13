package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping
public class SignupController {

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void save(@RequestBody SignupRequest signupRequest) throws IOException, AlreadyExistingUser {
        String givenEmail = signupRequest.getEmail();
        Optional<Account> byEmail = accountRepository.findByEmail(givenEmail);
        if(byEmail.isPresent()){
            throw new AlreadyExistingUser();
        }
        accountRepository.save(new Account(signupRequest));
    }
}
