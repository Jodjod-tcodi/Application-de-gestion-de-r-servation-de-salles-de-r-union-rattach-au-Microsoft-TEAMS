// === MeetingRoomService.java ===
package com.aya.meetingapp.MeetingBooking.service;

import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import com.aya.meetingapp.MeetingBooking.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingRoomService {

    private final MeetingRoomRepository roomRepo;

    @Autowired
    public MeetingRoomService(MeetingRoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public List<MeetingRoom> getAllRooms() {
        return roomRepo.findAll();
    }

    public MeetingRoom addRoom(MeetingRoom room) {
        return roomRepo.save(room);
    }

    public Optional<MeetingRoom> getRoomById(Long id) {
        return roomRepo.findById(id);
    }

    public MeetingRoom updateRoom(Long id, MeetingRoom updatedRoom) {
        return roomRepo.findById(id).map(room -> {
            room.setRoomName(updatedRoom.getRoomName());
            room.setRoomLocaion(updatedRoom.getRoomLocaion());
            room.setCapacity(updatedRoom.getCapacity());
            return roomRepo.save(room);
        }).orElseThrow(() -> new IllegalArgumentException("Room not found"));
    }

    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
