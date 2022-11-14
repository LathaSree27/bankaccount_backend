package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.repo.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AccountService {

    AccountRepository accountRepository;
    public Map<String,String > getSummary(String email) {
        Account account = accountRepository.findByEmail(email).get();
        Map<String,String> summaryResponse = new HashMap<>();
        summaryResponse.put("Account Number", String.valueOf(account.getId()));
        summaryResponse.put("Account Holder Name",account.getName());
        summaryResponse.put("Balance", String.valueOf(account.getBalance()));

        return  summaryResponse;
    }
}
