package com.smarthomestay.app.adapter.in.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {

    private int reservationNumber;
    private String email;

}
