package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.controller.request.SignupRequest;
import com.bankaccount.bankaccount.exception.AlreadyExistingUser;
import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignupService {

    AccountRepository accountRepository;

    public void save(SignupRequest signupRequest) throws AlreadyExistingUser {
        String givenEmail = signupRequest.getEmail();
        Optional<Account> byEmail = accountRepository.findByEmail(givenEmail);
        if(byEmail.isPresent()){
            throw new AlreadyExistingUser();
        }
        Account account = new Account(signupRequest);
        accountRepository.save(account);
    }
}
