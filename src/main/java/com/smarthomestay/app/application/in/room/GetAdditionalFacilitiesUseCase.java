package com.smarthomestay.app.application.in.room;

import com.smarthomestay.app.domain.RoomAdditionalFacilities;

import java.util.List;

public interface GetAdditionalFacilitiesUseCase {

    List<RoomAdditionalFacilities> findAllAdditionalFacilities();
}
