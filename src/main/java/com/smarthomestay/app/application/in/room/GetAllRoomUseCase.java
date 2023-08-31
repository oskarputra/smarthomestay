package com.smarthomestay.app.application.in.room;

import com.smarthomestay.app.domain.Room;

import java.util.List;

public interface GetAllRoomUseCase {

    List<Room> findAllRoom();

}
