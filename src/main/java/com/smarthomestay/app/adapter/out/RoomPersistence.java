package com.smarthomestay.app.adapter.out;


import com.smarthomestay.app.adapter.out.jpa.AdditionalFacilitiesRepository;
import com.smarthomestay.app.adapter.out.jpa.ReservationRepository;
import com.smarthomestay.app.adapter.out.jpa.RoomRepository;
import com.smarthomestay.app.application.out.reservation.CheckInOut;
import com.smarthomestay.app.application.out.reservation.CheckOutOut;
import com.smarthomestay.app.application.out.room.*;
import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class RoomPersistence implements GetAllRoomOut, GetRoomByTypeOut, GetAdditionalFacilitiesOut, AddRoomAdditionalFacilitiesOut, GetRoomByIdOut, CheckInOut, CheckOutOut {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;


    private final AdditionalFacilitiesRepository additionalFacilitiesRepository;

    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findByIsAvailable(true);
    }

    @Override
    public List<Room> findByRoomType(int type) {
        return roomRepository.findByRoomTypeIdIs(type);
    }

    @Override
    public List<RoomAdditionalFacilities> findAllAdditionalFacilities() {
        return additionalFacilitiesRepository.findAll();
    }

    @Override
    public Room addRoomAdditionalFacilities(List<RoomAdditionalFacilities> facilities) {
        return null;
    }

    @Override
    public Room findRoomById(long id) {
        Optional<Room> result = roomRepository.findById(id);

        if(result.isPresent()){
            Room room = roomRepository.findById(id).get();
            return room;
        }else{
            return null;
        }
    }

    @Override
    public Room checkIn(Reservation reservation, Room room) {
        reservationRepository.save(reservation);
        return roomRepository.save(room);
    }

    @Override
    public Room checkOut(Room room) {
        return roomRepository.save(room);
    }
}
