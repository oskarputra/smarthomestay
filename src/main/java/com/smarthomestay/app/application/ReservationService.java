package com.smarthomestay.app.application;

import com.smarthomestay.app.application.in.reservation.GetAllReservationUseCase;
import com.smarthomestay.app.application.in.reservation.GetReservationByEmailUseCase;
import com.smarthomestay.app.application.in.reservation.PaymentUseCase;
import com.smarthomestay.app.application.in.reservation.ReserveRoomUseCase;
import com.smarthomestay.app.application.out.reservation.GetAllReservationOut;
import com.smarthomestay.app.application.out.reservation.GetReservationByEmailOut;
import com.smarthomestay.app.application.out.reservation.PaymentOut;
import com.smarthomestay.app.application.out.reservation.ReserveRoomOut;
import com.smarthomestay.app.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReserveRoomUseCase, GetAllReservationUseCase, GetReservationByEmailUseCase, PaymentUseCase {

    private final ReserveRoomOut reserveRoomOut;
    private final GetReservationByEmailOut getReservationByEmailOut;
    private final GetAllReservationOut getAllReservationOut;
    private final PaymentOut paymentOut;

    @Override
    public List<Reservation> getReservationByEmail(String email) {
        return getReservationByEmailOut.getReservationByEmail(email);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return getAllReservationOut.getAllReservation();
    }

    @Override
    public Reservation addToReservation(Reservation reservation) {
        return reserveRoomOut.addToReservation(reservation);
    }

    @Override
    public Reservation payment(String email, int reservationId) {
        return paymentOut.payment(email, reservationId);
    }
}
