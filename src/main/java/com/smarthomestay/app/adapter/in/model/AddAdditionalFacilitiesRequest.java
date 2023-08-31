package com.smarthomestay.app.adapter.in.model;

import com.smarthomestay.app.domain.RoomAdditionalFacilities;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddAdditionalFacilitiesRequest {

    private int roomId;
    private List<RoomAdditionalFacilities> additionalFacilitiesList;

}
