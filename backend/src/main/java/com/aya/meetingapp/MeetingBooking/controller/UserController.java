package com.aya.meetingapp.MeetingBooking.controller;

import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import com.aya.meetingapp.MeetingBooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/{userId}/book/{roomId}")
    public Reservation bookRoom(@PathVariable Long userId,
                                @PathVariable Long roomId,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return userService.bookMeetingRoom(userId, roomId, start, end);
    }

    @DeleteMapping("/cancel/{reservationId}")
    public void cancelReservation(@PathVariable Long reservationId) {
        userService.cancelReservation(reservationId);
    }

    @GetMapping("/{userId}/reservations")
    public List<Reservation> getUserReservations(@PathVariable Long userId) {
        return userService.getUserReservations(userId);
    }
}
