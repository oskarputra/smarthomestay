package com.smarthomestay.app.application.out.room;

import com.smarthomestay.app.domain.RoomAdditionalFacilities;

import java.util.List;

public interface GetAdditionalFacilitiesOut {

    List<RoomAdditionalFacilities> findAllAdditionalFacilities();

}
