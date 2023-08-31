package com.smarthomestay.app.adapter.out;


import com.smarthomestay.app.adapter.out.jpa.ReservationRepository;
import com.smarthomestay.app.application.out.reservation.GetAllReservationOut;
import com.smarthomestay.app.application.out.reservation.GetReservationByEmailOut;
import com.smarthomestay.app.application.out.reservation.PaymentOut;
import com.smarthomestay.app.application.out.reservation.ReserveRoomOut;
import com.smarthomestay.app.domain.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ReservationPersistence implements GetReservationByEmailOut, GetAllReservationOut, ReserveRoomOut, PaymentOut {

    private final ReservationRepository reservationRepository;


    @Override
    public List<Reservation> getReservationByEmail(String email) {
        List<Reservation> list = reservationRepository.findReservationByEmail(email);
        return list;
    }

    @Override
    public List<Reservation> getAllReservation() {
        List<Reservation> list = reservationRepository.findAll();
        return list;
    }

    @Override
    public Reservation addToReservation(Reservation reservation) {

        Reservation res = reservationRepository.save(reservation);
        return res;
    }

    @Override
    public Reservation payment(String email, int reservationNumber) {
        Reservation reservation = reservationRepository.findReservationByEmailAndReservationNumberAndPaymentExpired(email, reservationNumber, false);

        if(reservation == null){
            return null;
        }

        if(reservation.isPaymentStatus()){
            return null;
        }else{
            reservation.setPaymentStatus(true);
            reservation.setPaymentExpired(true);

            reservationRepository.save(reservation);

            return reservation;
        }

    }


}
