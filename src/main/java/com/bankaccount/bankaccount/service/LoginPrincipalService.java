package com.bankaccount.bankaccount.service;

import com.bankaccount.bankaccount.model.Account;
import com.bankaccount.bankaccount.model.LoginPrincipal;
import com.bankaccount.bankaccount.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginPrincipalService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public LoginPrincipalService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = findAccountByEmail(email);
        return new LoginPrincipal(account);
    }

    public long getAccountId(String email) {
        Account account = findAccountByEmail(email);
        return account.getId();
    }

    private Account findAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));

    }
}
