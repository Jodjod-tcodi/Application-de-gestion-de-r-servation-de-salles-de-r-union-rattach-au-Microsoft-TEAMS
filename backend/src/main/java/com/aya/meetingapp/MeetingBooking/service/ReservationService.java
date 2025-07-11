
package com.aya.meetingapp.MeetingBooking.service;

import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import com.aya.meetingapp.MeetingBooking.repository.MeetingRoomRepository;
import com.aya.meetingapp.MeetingBooking.repository.ReservationRepository;
import com.aya.meetingapp.MeetingBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final UserRepository userRepo;
    private final MeetingRoomRepository roomRepo;

    @Autowired
    public ReservationService(ReservationRepository reservationRepo,
                              UserRepository userRepo,
                              MeetingRoomRepository roomRepo) {
        this.reservationRepo = reservationRepo;
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
    }

    public Reservation bookRoom(Long userId, Long roomId, LocalDateTime start, LocalDateTime end) {
        Optional<AppUser> userOpt = userRepo.findById(userId);
        Optional<MeetingRoom> roomOpt = roomRepo.findById(roomId);

        if (userOpt.isEmpty() || roomOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Room not found");
        }

        LocalDate date = start.toLocalDate();
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        List<Reservation> conflicts = reservationRepo.findConflictingReservations(roomId, date, startTime, endTime);
        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Room is already booked during this time.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(userOpt.get());
        reservation.setMeetingRoom(roomOpt.get());
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setStatus("BOOKED");

        return reservationRepo.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        Optional<Reservation> reservation = reservationRepo.findById(reservationId);
        if (reservation.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }
        reservationRepo.deleteById(reservationId);
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepo.findByUser_Id(userId);
    }

    public List<Reservation> getRoomReservations(Long roomId) {
        return reservationRepo.findByMeetingRoom_RoomId(roomId);
    }

    public boolean isRoomAvailable(Long roomId, LocalDateTime start, LocalDateTime end) {
        LocalDate date = start.toLocalDate();
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();
        List<Reservation> conflicts = reservationRepo.findConflictingReservations(roomId, date, startTime, endTime);
        return conflicts.isEmpty();
    }

}

