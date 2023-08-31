package com.smarthomestay.app.application.in.reservation;

import com.smarthomestay.app.domain.Reservation;

import java.util.List;

public interface GetAllReservationUseCase {

    List<Reservation> getAllReservation();

}
