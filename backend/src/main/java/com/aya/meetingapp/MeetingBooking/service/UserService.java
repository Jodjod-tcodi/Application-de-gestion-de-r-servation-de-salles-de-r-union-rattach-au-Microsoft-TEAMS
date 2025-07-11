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
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       MeetingRoomRepository meetingRoomRepository,
                       ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Reservation bookMeetingRoom(Long userId, Long roomId, LocalDateTime start, LocalDateTime end) {
        Optional<AppUser> userOpt = userRepository.findById(userId);
        Optional<MeetingRoom> roomOpt = meetingRoomRepository.findById(roomId);

        if (userOpt.isEmpty() || roomOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Room not found");
        }

        LocalDate date = start.toLocalDate();
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();


        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(roomId, date, startTime, endTime);
        if (!conflictingReservations.isEmpty()) {
            throw new IllegalStateException("Room is already booked during this time.");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(userOpt.get());
        reservation.setMeetingRoom(roomOpt.get());
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setStatus("BOOKED");

        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Long reservationId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isEmpty()) {
            throw new IllegalArgumentException("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }

    public List<MeetingRoom> getAvailableRooms(LocalDate date, LocalTime start, LocalTime end) {
        List<MeetingRoom> allRooms = meetingRoomRepository.findAll();


        return allRooms.stream()
                .filter(room -> {
                    List<Reservation> conflicts = reservationRepository.findConflictingReservations(
                            room.getRoomId(), date, start, end);
                    return conflicts.isEmpty();
                })
                .collect(Collectors.toList());
    }

    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findReservationsByUserId(userId);
    }
}
