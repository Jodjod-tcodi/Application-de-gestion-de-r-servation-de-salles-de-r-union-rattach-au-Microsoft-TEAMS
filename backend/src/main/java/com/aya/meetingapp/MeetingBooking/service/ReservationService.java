package com.aya.meetingapp.MeetingBooking.service;

import com.aya.meetingapp.MeetingBooking.dto.ReservationRequestDto;
import com.aya.meetingapp.MeetingBooking.dto.ReservationResponseDto;
import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.entity.MeetingRoom;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import com.aya.meetingapp.MeetingBooking.mapper.ReservationMapper;
import com.aya.meetingapp.MeetingBooking.repository.MeetingRoomRepository;
import com.aya.meetingapp.MeetingBooking.repository.ReservationRepository;
import com.aya.meetingapp.MeetingBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final UserRepository userRepo;
    private final MeetingRoomRepository roomRepo;
    private final ReservationMapper reservationMapper;
    private final  EmailService emailService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepo,
                              UserRepository userRepo,
                              MeetingRoomRepository roomRepo,
                              ReservationMapper reservationMapper,
                              EmailService  emailService) {
        this.reservationRepo = reservationRepo;
        this.userRepo = userRepo;
        this.roomRepo = roomRepo;
        this.reservationMapper = reservationMapper;
        this.emailService = emailService;
    }

//logic
    //room booking checking validity of users , rooms and conflicts
    public ReservationResponseDto bookRoom(ReservationRequestDto request) {
        Optional<AppUser> userOpt = userRepo.findById(request.getUserId());
        Optional<MeetingRoom> roomOpt = roomRepo.findById(request.getRoomId());

        if (userOpt.isEmpty() || roomOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Room not found"); //after this msg what happens ? do i need to code sth else to restart the process of booking or it restarts auto ?
        }

        LocalDate date = request.getDate();
        LocalTime startTime = request.getStartTime();
        LocalTime endTime = request.getEndTime();

        List<Reservation> conflicts = reservationRepo.findConflictingReservations(
                request.getRoomId(), date, startTime, endTime);

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

        Reservation saved = reservationRepo.save(reservation);

        String userEmail = userOpt.get().getEmail();
        String subject = "Reservation Confirmation";
        String body = "Your reservation for room '" + roomOpt.get().getRoomName() + "' on " +
                date + " from " + startTime + " to " + endTime + " has been confirmed.";

        emailService.sendConfirmationEmail(userEmail, subject, body);

        return reservationMapper.toDto(saved);
    }
    public ReservationResponseDto updateReservation(Long id, ReservationRequestDto dto) {
        Reservation existing = reservationRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        // Check for conflicts excluding the current reservation
        List<Reservation> conflicts = reservationRepo.findConflictingReservations(
                        existing.getMeetingRoom().getRoomId(),
                        dto.getDate(),
                        dto.getStartTime(),
                        dto.getEndTime()
                ).stream()
                .filter(r -> !r.getReservationId().equals(existing.getReservationId()))
                .toList();

        if (!conflicts.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Updated time conflicts with another reservation.");
        }

        existing.setStartTime(dto.getStartTime());
        existing.setEndTime(dto.getEndTime());
        existing.setDate(dto.getDate());

        Reservation updated = reservationRepo.save(existing);
        return reservationMapper.toDto(updated);

    }

    public Map<String, String> cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        reservationRepo.delete(reservation);

        try {
            String email = reservation.getUser().getEmail();
            String subject = "Reservation Cancelled";
            String body = "Your reservation for room " + reservation.getMeetingRoom().getRoomName() + " on " +
                    reservation.getDate() + " from " + reservation.getStartTime() + " to " + reservation.getEndTime() +
                    " has been cancelled.";
            emailService.sendConfirmationEmail(email, subject, body);
        } catch (Exception ignored) {}

        return Map.of("message", "Reservation deleted successfully");
    }


    public List<ReservationResponseDto> getUserReservations(Long userId) {
        return reservationRepo.findByUser_Id(userId)
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ReservationResponseDto> getRoomReservations(Long roomId) {
        return reservationRepo.findByMeetingRoom_RoomId(roomId)
                .stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<ReservationResponseDto> getAllReservations() {
        return reservationRepo.findAll().stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    // --- add these public helper methods to ReservationService ---

    public Long getReservationOwnerId(Long reservationId) {
        return reservationRepo.findById(reservationId)
                .map(r -> r.getUser().getId())
                .orElse(null);
    }

    public boolean isUserOwner(String username, Long ownerId) {
        return userRepo.findByUsername(username)
                .map(user -> user.getId().equals(ownerId))
                .orElse(false);
    }


    //needs this to replace conflict logic later
    public boolean isRoomAvailable(Long roomId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        List<Reservation> conflicts = reservationRepo.findConflictingReservations(roomId, date, startTime, endTime);
        return conflicts.isEmpty();
    }



}


