package com.bankaccount.bankaccount.controller;

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

    @PostMapping("/credit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void credit(Principal principal, @RequestParam(value = "amount") BigDecimal amount) throws Exception {
        String email = principal.getName();
        transactionService.credit(amount, email);
    }

    @PostMapping("/debit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void debit(Principal principal, @RequestParam(value = "amount") BigDecimal amount) throws Exception {
        String email = principal.getName();
        transactionService.debit(amount, email);
    }
}
