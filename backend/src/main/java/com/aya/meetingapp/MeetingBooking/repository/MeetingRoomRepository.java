package com.aya.meetingapp.MeetingBooking.repository;

import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository
        extends JpaRepository<MeetingRoom, Long> {
}
