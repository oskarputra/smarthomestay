package com.smarthomestay.app.application.in.reservation;

import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;

public interface CheckInUseCase {

    Room checkIn(Reservation reservation, Room room);
}
