package com.smarthomestay.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationNumber;

    @OneToMany
    private List<Room> roomList;

    private BigDecimal roomPrice;

    private BigDecimal additionalPrice;

    private BigDecimal totalPrice;

    private Date reservationDate;

    private String email;

    private boolean paymentStatus;

    private boolean paymentExpired;


}
