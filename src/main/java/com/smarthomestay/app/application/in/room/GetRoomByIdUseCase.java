package com.smarthomestay.app.application.in.room;

import com.smarthomestay.app.domain.Room;

public interface GetRoomByIdUseCase {

    Room findRoomById(long id);
}
