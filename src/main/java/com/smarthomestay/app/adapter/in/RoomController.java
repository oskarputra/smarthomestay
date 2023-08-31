package com.smarthomestay.app.adapter.in;

import com.smarthomestay.app.adapter.in.model.CheckInRequest;
import com.smarthomestay.app.adapter.in.model.CheckOutRequest;
import com.smarthomestay.app.adapter.out.mapper.ErrorMapper;
import com.smarthomestay.app.adapter.out.model.GeneralResponse;
import com.smarthomestay.app.application.in.reservation.CheckInUseCase;
import com.smarthomestay.app.application.in.reservation.CheckOutUseCase;
import com.smarthomestay.app.application.in.reservation.GetReservationByEmailUseCase;
import com.smarthomestay.app.application.in.room.GetAdditionalFacilitiesUseCase;
import com.smarthomestay.app.application.in.room.GetAllRoomUseCase;
import com.smarthomestay.app.application.in.room.GetRoomByIdUseCase;
import com.smarthomestay.app.application.in.room.GetRoomByTypeUseCase;
import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/room")
public class RoomController {

    private final GetAllRoomUseCase getAllRoomUseCase;

    private final GetRoomByTypeUseCase getRoomByTypeUseCase;

    private final GetRoomByIdUseCase roomByIdUseCase;

    private final CheckInUseCase checkInUseCase;

    private final CheckOutUseCase checkOutUseCase;

    private final GetAdditionalFacilitiesUseCase getAdditionalFacilitiesUseCase;

    private final ErrorMapper errorMapper;

    private final GetReservationByEmailUseCase getReservationByEmailUseCase;


    @GetMapping("")
    public ResponseEntity<GeneralResponse> searchRoom() {

        try {
            System.out.println("masuk");
            List<Room> rooms = getAllRoomUseCase.findAllRoom();
            System.out.println(rooms);
            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message("Success").
                    data(rooms).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/{type}")
    public ResponseEntity<GeneralResponse> searchRoomByRoomType(@PathVariable int type) {

        try {
            List<Room> rooms = getRoomByTypeUseCase.findByRoomType(type);

            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message("Success").
                    data(rooms).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }

    }

    @GetMapping("/additional-facilities")
    public ResponseEntity<GeneralResponse> searchRoomAdditionalFacilities() {

        try {
            List<RoomAdditionalFacilities> additionalFacilities = getAdditionalFacilitiesUseCase.findAllAdditionalFacilities();

            return new ResponseEntity<>(GeneralResponse.builder().
                    code(HttpStatus.OK.value()).
                    message("Success").
                    data(additionalFacilities).
                    build(), HttpStatus.OK);
        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }



    @PostMapping("/check-in")
    public ResponseEntity<GeneralResponse> checkIn(@RequestBody CheckInRequest checkInRequest) {

        try {
            List<Reservation> reservations = getReservationByEmailUseCase.getReservationByEmail(checkInRequest.getEmail());
            Object data = new ArrayList<>();
            String message = "Success";

            boolean isReserved = true;

            if(reservations == null){
                isReserved = false;
            }

            Room room = new Room();
            if(isReserved){
                room = roomByIdUseCase.findRoomById(checkInRequest.getRoomId());
                if(room == null){
                    isReserved = false;
                }
            }

            Reservation rsv = new Reservation();
            if(isReserved){
                isReserved = false;
                if(room.isAvailable()){
                    for(Reservation rs:reservations){
                        rsv = rs;
                        if(rsv.isPaymentStatus() && !rsv.isPaymentExpired()){
                            for(Room r:rsv.getRoomList()){
                                if(r.equals(room)){
                                    isReserved = true;
                                    break;
                                }
                            }
                        }
                    }
                }

            }

            if(!isReserved){
                message = "You don't have any reservation, please call our customer service if you already make a reservation";
                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.OK.value()).
                        message(message).
                        data(data).
                        build(), HttpStatus.OK);
            }else{

                room.setAvailable(false);
                room.setCheckInDate(new Date());
                room.setCheckOutDate(null);

                Room result = checkInUseCase.checkIn(rsv, room);

                data = result;

                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.OK.value()).
                        message(message).
                        data(data).
                        build(), HttpStatus.OK);
            }


        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/check-out")
    public ResponseEntity<GeneralResponse> checkOut(@RequestBody CheckOutRequest checkOutRequest) {
        try {
            Object data = new ArrayList<>();
            String message = "Success";

            Room room = roomByIdUseCase.findRoomById(checkOutRequest.getRoomId());
            if(room == null){
                message = "Room not valid. Please try again";
                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.OK.value()).
                        message(message).
                        data(data).
                        build(), HttpStatus.OK);
            }else{

                room.setAvailable(true);
                room.setCheckOutDate(new Date());
                room.setCheckInDate(null);

                Room result = checkOutUseCase.checkOut(room);

                data = result;

                return new ResponseEntity<>(GeneralResponse.builder().
                        code(HttpStatus.OK.value()).
                        message(message).
                        data(data).
                        build(), HttpStatus.OK);
            }


        } catch (Exception e) {
            return errorMapper.toError(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }


}
