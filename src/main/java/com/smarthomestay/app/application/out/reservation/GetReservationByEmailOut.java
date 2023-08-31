package com.smarthomestay.app.application.out.reservation;

import com.smarthomestay.app.domain.Reservation;

import java.util.List;

public interface GetReservationByEmailOut {

    List<Reservation> getReservationByEmail(String email);
}
