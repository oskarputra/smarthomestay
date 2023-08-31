package com.smarthomestay.app.application.out.reservation;

import com.smarthomestay.app.domain.Reservation;

public interface PaymentOut {

    Reservation payment(String email, int reservationId);

}
