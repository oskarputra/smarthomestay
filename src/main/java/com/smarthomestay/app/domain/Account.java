package com.smarthomestay.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Account {

    private String name;

    private String password;

    @Id
    private String email;

    private String phoneNumber;

    private boolean isGuest;

}
