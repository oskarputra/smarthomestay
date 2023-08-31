package com.smarthomestay.app.application.in.reservation;

import com.smarthomestay.app.domain.Reservation;

public interface PaymentUseCase {

    Reservation payment(String email, int reservationId);

}
