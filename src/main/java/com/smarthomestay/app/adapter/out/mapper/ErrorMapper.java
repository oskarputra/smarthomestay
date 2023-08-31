package com.smarthomestay.app.adapter.out.mapper;

import com.smarthomestay.app.adapter.out.model.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ErrorMapper {

    public ResponseEntity<GeneralResponse> toError(HttpStatus code, String message){
        return new ResponseEntity<>(GeneralResponse.builder().
                code(code.value()).
                message(message).
                data(new ArrayList<>()).
                build(), code);
    }
}
