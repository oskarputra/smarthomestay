package com.smarthomestay.app.application.in.room;

import com.smarthomestay.app.domain.Room;

import java.util.List;

public interface GetRoomByTypeUseCase {

    List<Room> findByRoomType(int type);
}
