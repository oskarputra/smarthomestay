package com.smarthomestay.app.adapter.out.jpa;

import com.smarthomestay.app.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findReservationByEmail(String email);
    Reservation findReservationByEmailAndReservationNumberAndPaymentExpired(String email, int reservationNumber, boolean isExpired);

}
