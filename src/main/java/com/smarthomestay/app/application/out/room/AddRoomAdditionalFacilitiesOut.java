package com.smarthomestay.app.application.out.room;

import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;

import java.util.List;

public interface AddRoomAdditionalFacilitiesOut {

    Room addRoomAdditionalFacilities(List<RoomAdditionalFacilities> facilities);
}
