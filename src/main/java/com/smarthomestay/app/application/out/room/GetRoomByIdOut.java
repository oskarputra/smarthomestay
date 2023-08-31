package com.smarthomestay.app.application.out.room;

import com.smarthomestay.app.domain.Room;

public interface GetRoomByIdOut {

    Room findRoomById(long id);
}
