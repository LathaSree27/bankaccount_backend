package com.bankaccount.bankaccount.controller;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.Transaction;
import com.bankaccount.bankaccount.repo.AccountRepository;
import com.bankaccount.bankaccount.repo.TransactionRepository;
import com.bankaccount.bankaccount.service.LoginPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    LoginPrincipalService loginPrincipalService;

    @PostMapping("/credit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void login(Principal principal, @RequestParam(value = "amount") BigDecimal amount){
        String email = principal.getName();
        long accountId = loginPrincipalService.getAccountId(email);
        Transaction credit = new Transaction("CREDIT", amount, accountRepository.findById(accountId).get());
        transactionRepository.save(credit);
        System.out.println("email"+email);
//        transactionRepository.save("CREDIT",)
//        Map<String, Object> userDetails = new HashMap<>();
//        userDetails.put("email", email);
//        return userDetails;

    }
}
