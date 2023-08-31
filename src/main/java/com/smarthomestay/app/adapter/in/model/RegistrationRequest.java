package com.smarthomestay.app.adapter.in.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationRequest {

    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private boolean isGuest;

}
