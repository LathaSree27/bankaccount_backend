package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.service.LoginPrincipalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {
    AccountRepository accountRepository;

    @GetMapping("/summary")
    public Map<String,String > summary(Principal principal){
        Map<String, String> summaryResponse = new HashMap<>();
        String email = principal.getName();
        Account account = accountRepository.findByEmail(email).get();
        summaryResponse.put("Account Number", String.valueOf(account.getId()));
        summaryResponse.put("Account Holder Name",account.getName());
        summaryResponse.put("Balance", String.valueOf(account.getBalance()));

        return summaryResponse;


    }
}
