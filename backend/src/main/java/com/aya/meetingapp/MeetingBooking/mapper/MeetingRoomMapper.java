package com.aya.meetingapp.MeetingBooking.mapper;

import com.aya.meetingapp.MeetingBooking.dto.MeetingRoomDto;
import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import org.springframework.stereotype.Component;

@Component
public class MeetingRoomMapper {

    public MeetingRoomDto toDto(MeetingRoom room) {
        return new MeetingRoomDto(
                room.getRoomName(),
                room.getRoomLocation(),
                room.getCapacity()
        );
    }

    public MeetingRoom toEntity(MeetingRoomDto dto) {
        MeetingRoom room = new MeetingRoom();
        room.setRoomName(dto.getRoomName());
        room.setRoomLocation(dto.getRoomLocation());
        room.setCapacity(dto.getCapacity());
        return room;
    }
}
