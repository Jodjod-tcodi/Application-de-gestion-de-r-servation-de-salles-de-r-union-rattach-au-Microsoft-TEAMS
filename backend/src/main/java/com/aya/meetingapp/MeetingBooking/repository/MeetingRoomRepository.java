package com.aya.meetingapp.MeetingBooking.repository;

import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRoomRepository
        extends JpaRepository<MeetingRoom, Long> {
    // in MeetingRoomRepository interface (already extends JpaRepository<MeetingRoom, Long>)
    List<MeetingRoom> findByCapacityGreaterThanEqual(int capacity);
    List<MeetingRoom> findByRoomLocationContainingIgnoreCase(String location);
    List<MeetingRoom> findByCapacityGreaterThanEqualAndRoomLocationContainingIgnoreCase(int capacity, String location);

}
