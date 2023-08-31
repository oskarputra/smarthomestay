package com.smarthomestay.app.adapter.in.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckInRequest {

    private long roomId;
    private String email;

}
