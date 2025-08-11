package com.aya.meetingapp.MeetingBooking.mapper;

import com.aya.meetingapp.MeetingBooking.dto.ReservationRequestDto;
import com.aya.meetingapp.MeetingBooking.dto.ReservationResponseDto;
import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import org.springframework.stereotype.Component;

import com.aya.meetingapp.MeetingBooking.dto.ReservationResponseDto;
import com.aya.meetingapp.MeetingBooking.dto.MeetingRoomDto;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;

@Component
public class ReservationMapper {

    public ReservationResponseDto toDto(Reservation reservation) {
        ReservationResponseDto dto = new ReservationResponseDto();
        dto.setReservationId(reservation.getReservationId());
        dto.setUserId(reservation.getUser().getId());
        dto.setRoomId(reservation.getMeetingRoom().getRoomId());
        dto.setRoomName(reservation.getMeetingRoom().getRoomName());
        dto.setDate(reservation.getDate());
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setStatus(reservation.getStatus());
        return dto;
    }
    public Reservation toEntity(ReservationRequestDto req, AppUser user, MeetingRoom room) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setMeetingRoom(room);
        reservation.setDate(req.getDate());
        reservation.setStartTime(req.getStartTime());
        reservation.setEndTime(req.getEndTime());
        // status set by service
        return reservation;
    }






}
