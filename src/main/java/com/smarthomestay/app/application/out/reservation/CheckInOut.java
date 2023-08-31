package com.smarthomestay.app.application.out.reservation;

import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;

public interface CheckInOut {

    Room checkIn(Reservation reservation, Room room);
}