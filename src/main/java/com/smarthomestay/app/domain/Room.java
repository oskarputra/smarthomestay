package com.smarthomestay.app.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private RoomType roomType;

    @OneToMany
    private List<RoomMainFacilities> roomFacilities;

    @OneToMany
    private List<RoomAdditionalFacilities> roomAdditionalFacilities;

    private String roomNumber;

    private String numberOfGuest;

    private BigDecimal price;

    private boolean isAvailable;

    private Date checkInDate;

    private Date checkOutDate;

}
