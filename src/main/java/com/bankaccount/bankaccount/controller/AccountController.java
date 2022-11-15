package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {
    AccountService accountService;

    @GetMapping("/summary")
    public Map<String, String> summary(Principal principal) {
        String email = principal.getName();
        Map<String, String> summaryResponse = accountService.getSummary(email);
        return summaryResponse;
    }
}
