package com.aya.meetingapp.MeetingBooking.controller;

import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import com.aya.meetingapp.MeetingBooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public Reservation createReservation(
            @RequestParam Long userId,
            @RequestParam Long roomId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return reservationService.bookRoom(userId, roomId, start, end);
    }


    @DeleteMapping("/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
    }


    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsForUser(@PathVariable Long userId) {
        return reservationService.getUserReservations(userId);
    }


    @GetMapping("/room/{roomId}")
    public List<Reservation> getReservationsForRoom(@PathVariable Long roomId) {
        return reservationService.getRoomReservations(roomId);
    }
}
