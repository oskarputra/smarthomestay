package com.smarthomestay.app.adapter.in;

import com.smarthomestay.app.adapter.in.model.RegistrationRequest;
import com.smarthomestay.app.adapter.out.mapper.ErrorMapper;
import com.smarthomestay.app.adapter.out.model.GeneralResponse;
import com.smarthomestay.app.application.in.users.RegistrationUseCase;
import com.smarthomestay.app.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/account")
public class AccountController {

    private final RegistrationUseCase registrationUseCase;
    private final ErrorMapper errorMapper;

    private final BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> registerUser(@RequestBody RegistrationRequest request) {

        try {
            Account account = new Account();
            account.setName(request.getName());
            account.setEmail(request.getEmail());
            account.setPassword(passwordEncoder.encode(request.getPassword()));
            account.setPhoneNumber(request.getPhoneNumber());

            Account result = registrationUseCase.registerUser(account);
            if(result == null){
                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.CREATED.value()).
                        message("Success").
                        data(new ArrayList<>()).
                        build(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.CONFLICT.value()).
                        message("Account already exist").
                        data(new ArrayList<>()).
                        build(), HttpStatus.CONFLICT);
            }


        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}
