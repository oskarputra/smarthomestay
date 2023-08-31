package com.smarthomestay.app.adapter.in;

import com.smarthomestay.app.adapter.in.model.PaymentRequest;
import com.smarthomestay.app.adapter.in.model.ReservationRequest;
import com.smarthomestay.app.adapter.out.mapper.ErrorMapper;
import com.smarthomestay.app.adapter.out.model.GeneralResponse;
import com.smarthomestay.app.application.in.reservation.GetAllReservationUseCase;
import com.smarthomestay.app.application.in.reservation.GetReservationByEmailUseCase;
import com.smarthomestay.app.application.in.reservation.PaymentUseCase;
import com.smarthomestay.app.application.in.reservation.ReserveRoomUseCase;
import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/reservation")
public class ReservationController {

    private final ReserveRoomUseCase reserveRoomUseCase;

    private final GetAllReservationUseCase getAllReservationUseCase;

    private final GetReservationByEmailUseCase getReservationByEmailUseCase;

    private final PaymentUseCase paymentUseCase;

    private final ErrorMapper errorMapper;

    @GetMapping("")
    public ResponseEntity<GeneralResponse> getAllReservation() {

        try {
            List<Reservation> reservations = getAllReservationUseCase.getAllReservation();
            String message = "Success";
            if(reservations == null || reservations.isEmpty()){
                reservations = new ArrayList<>();
                message = "There is no reservation in the system";
            }

            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(message).
                    data(reservations).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<GeneralResponse> getReservationByEmail(@PathVariable String email) {

        try {
            List<Reservation> reservations = getReservationByEmailUseCase.getReservationByEmail(email);
            if(reservations == null){
                reservations = new ArrayList<>();
            }
            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message("Success").
                    data(reservations).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }


    @PostMapping("")
    public ResponseEntity<GeneralResponse> reserve(@RequestBody ReservationRequest reservationRequest) {

        try {
            BigDecimal totalRoomPrice = new BigDecimal(0);
            BigDecimal totalAdditionalPrice = new BigDecimal(0);

            for(Room r:reservationRequest.getListRoom()){
                totalRoomPrice = totalRoomPrice.add(r.getPrice());
                for(RoomAdditionalFacilities ra: r.getRoomAdditionalFacilities()){
                    totalAdditionalPrice = totalAdditionalPrice.add(ra.getPrice());
                }
            }

            BigDecimal totalPrice = totalRoomPrice.add(totalAdditionalPrice);
            Reservation r = new Reservation();
            r.setReservationDate(new Date());
            r.setRoomList(reservationRequest.getListRoom()) ;
            r.setEmail(reservationRequest.getEmail());
            r.setRoomPrice(totalRoomPrice);
            r.setAdditionalPrice(totalAdditionalPrice);
            r.setTotalPrice(totalPrice);

            Reservation reservations = reserveRoomUseCase.addToReservation(r);

            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message("Success").
                    data(reservations).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<GeneralResponse> payment(@RequestBody PaymentRequest paymentRequest) {

        try {
            Reservation reservation = paymentUseCase.payment(paymentRequest.getEmail(), paymentRequest.getReservationNumber());
            Object data = reservation;
            String message = "Success";

            if(data == null){
                data = new ArrayList<>();
                message = "You don't have any active payment or has already paid";
            }

            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message(message).
                    data(data).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }


}
