package com.smarthomestay.app.application;

import com.smarthomestay.app.application.in.reservation.CheckInUseCase;
import com.smarthomestay.app.application.in.reservation.CheckOutUseCase;
import com.smarthomestay.app.application.in.room.*;
import com.smarthomestay.app.application.out.reservation.CheckInOut;
import com.smarthomestay.app.application.out.reservation.CheckOutOut;
import com.smarthomestay.app.application.out.room.*;
import com.smarthomestay.app.domain.Reservation;
import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService implements GetAllRoomUseCase, GetRoomByTypeUseCase, GetAdditionalFacilitiesUseCase, AddRoomAdditionalFacilitiesUseCase, CheckInUseCase, CheckOutUseCase, GetRoomByIdUseCase {

    private final GetAllRoomOut getAllRoomOut;
    private final GetRoomByTypeOut getRoomByTypeOut;
    private final GetAdditionalFacilitiesOut getAdditionalFacilitiesOut;
    private final AddRoomAdditionalFacilitiesOut addRoomAdditionalFacilitiesOut;
    private final GetRoomByIdOut getRoomByIdOut;
    private final CheckInOut checkInOut;
    private final CheckOutOut checkOutOut;


    @Override
    public List<Room> findAllRoom() {
        List<Room> roomList = getAllRoomOut.findAllRoom();

        return roomList == null ? new ArrayList<>() : roomList;
    }

    @Override
    public List<Room> findByRoomType(int type) {
        return getRoomByTypeOut.findByRoomType(type);
    }

    @Override
    public List<RoomAdditionalFacilities> findAllAdditionalFacilities() {
        List<RoomAdditionalFacilities> roomList = getAdditionalFacilitiesOut.findAllAdditionalFacilities();

        return roomList == null ? new ArrayList<>() : roomList;
    }

    @Override
    public Room addRoomAdditionalFacilities(List<RoomAdditionalFacilities> facilities) {
        Room room = addRoomAdditionalFacilitiesOut.addRoomAdditionalFacilities(facilities);
        return room;
    }

    @Override
    public Room checkIn(Reservation reservation, Room room) {
        return checkInOut.checkIn(reservation,room);
    }


    @Override
    public Room checkOut(Room room) {
        return checkOutOut.checkOut(room);
    }

    @Override
    public Room findRoomById(long id) {
        return getRoomByIdOut.findRoomById(id);
    }

}
