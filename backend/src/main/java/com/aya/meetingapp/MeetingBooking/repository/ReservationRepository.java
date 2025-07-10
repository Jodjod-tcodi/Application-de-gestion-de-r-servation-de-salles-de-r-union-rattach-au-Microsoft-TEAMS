package com.aya.meetingapp.MeetingBooking.repository;

import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Find reservations conflicting with given room, date and time interval (exclude canceled)
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.meetingRoom.roomId = :roomId " +
            "AND r.date = :date " +
            "AND r.startTime < :endTime " +
            "AND r.endTime > :startTime " +
            "AND r.status <> 'CANCELED'")
    List<Reservation> findConflictingReservations(
            @Param("roomId") Long roomId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

    // Find all reservations by user ID
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
    List<Reservation> findReservationsByUserId(@Param("userId") Long userId);

    List<Reservation> findByUser_Id(Long userId);

    List<Reservation> findByMeetingRoom_RoomId(Long roomId);
}

