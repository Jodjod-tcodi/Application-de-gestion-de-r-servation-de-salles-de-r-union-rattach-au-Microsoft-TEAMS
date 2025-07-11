package com.aya.meetingapp.MeetingBooking.controller;

import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import com.aya.meetingapp.MeetingBooking.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class MeetingRoomController {

    private final MeetingRoomService roomService;

    @Autowired
    public MeetingRoomController(MeetingRoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public List<MeetingRoom> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/{id}")
    public MeetingRoom getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public MeetingRoom addRoom(@RequestBody MeetingRoom room) {
        return roomService.addRoom(room);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public MeetingRoom updateRoom(@PathVariable Long id, @RequestBody MeetingRoom updatedRoom) {
        return roomService.updateRoom(id, updatedRoom);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
