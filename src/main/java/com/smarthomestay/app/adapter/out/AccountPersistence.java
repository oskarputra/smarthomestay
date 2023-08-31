package com.smarthomestay.app.adapter.out;

import com.smarthomestay.app.adapter.out.jpa.AccountRepository;
import com.smarthomestay.app.application.out.users.RegistrationOut;
import com.smarthomestay.app.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountPersistence implements RegistrationOut {

    private final AccountRepository accountRepository;

    @Override
    public Account registerUser(Account account) {

        if(accountRepository.findById(account.getEmail()).isPresent()){
            return account;
        }else{
            Account result = accountRepository.save(account);
            return null;
        }

    }
}
