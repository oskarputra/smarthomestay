package com.smarthomestay.app.application.in.users;

import com.smarthomestay.app.domain.Account;

public interface RegistrationUseCase {

    Account registerUser(Account account);
}
