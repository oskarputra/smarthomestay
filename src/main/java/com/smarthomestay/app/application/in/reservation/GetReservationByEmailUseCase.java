package com.smarthomestay.app.application.in.reservation;

import com.smarthomestay.app.domain.Reservation;

import java.util.List;

public interface GetReservationByEmailUseCase {

    List<Reservation> getReservationByEmail(String email);
    
}
