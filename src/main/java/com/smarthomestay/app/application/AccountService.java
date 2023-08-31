package com.smarthomestay.app.application;

import com.smarthomestay.app.application.in.users.RegistrationUseCase;
import com.smarthomestay.app.application.out.users.RegistrationOut;
import com.smarthomestay.app.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements RegistrationUseCase {

    private final RegistrationOut registrationOut;

    @Override
    public Account registerUser(Account account) {
        account = registrationOut.registerUser(account);

        return account;
    }
}
