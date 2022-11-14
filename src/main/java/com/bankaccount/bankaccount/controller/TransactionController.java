package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.service.LoginPrincipalService;
import com.bankaccount.bankaccount.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
    TransactionService transactionService;
    LoginPrincipalService loginPrincipalService;

    @PostMapping("/credit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void credit(Principal principal, @RequestParam(value = "amount") BigDecimal amount) throws Exception {
        String email = principal.getName();
        long accountId = loginPrincipalService.getAccountId(email);
        transactionService.credit(amount,accountId);
    }
    @PostMapping("/debit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void debit(Principal principal, @RequestParam(value = "amount") BigDecimal amount) throws Exception {
        String email = principal.getName();
        long accountId = loginPrincipalService.getAccountId(email);
        transactionService.debit(amount,accountId);
    }
}
