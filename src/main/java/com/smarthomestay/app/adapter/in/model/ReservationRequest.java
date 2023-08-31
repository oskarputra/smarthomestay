package com.smarthomestay.app.adapter.in.model;

import com.smarthomestay.app.domain.Room;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReservationRequest {

    private String email;
    private List<Room> listRoom;

}
