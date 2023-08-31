package com.smarthomestay.app.application.out.room;

import com.smarthomestay.app.domain.Room;

import java.util.List;

public interface GetRoomByTypeOut {

    List<Room> findByRoomType(int type);

}
