package com.smarthomestay.app.application.in.room;

import com.smarthomestay.app.domain.Room;
import com.smarthomestay.app.domain.RoomAdditionalFacilities;

import java.util.List;

public interface AddRoomAdditionalFacilitiesUseCase {

    Room addRoomAdditionalFacilities(List<RoomAdditionalFacilities> facilities);
}
