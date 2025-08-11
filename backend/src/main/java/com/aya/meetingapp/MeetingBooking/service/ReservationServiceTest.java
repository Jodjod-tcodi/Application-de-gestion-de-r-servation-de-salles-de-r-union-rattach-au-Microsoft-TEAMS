//package com.aya.meetingapp.MeetingBooking.service;
//
//import com.aya.meetingapp.MeetingBooking.dto.ReservationRequestDto;
//import com.aya.meetingapp.MeetingBooking.entity.Reservation;
//import com.aya.meetingapp.MeetingBooking.repository.ReservationRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ReservationServiceTest {
//
//    @InjectMocks
//    private ReservationService reservationService;
//
//    @Mock
//    private ReservationRepository reservationRepository;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testBookRoomSuccess() {
//        ReservationRequestDto request = new ReservationRequestDto(1L, 2L,
//                LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
//
//        when(reservationRepository.findByRoomIdAndTimeOverlap(
//                anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
//                .thenReturn(Collections.emptyList());
//
//        Reservation reservation = new Reservation();
//        reservation.setId(1L);
//
//        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
//
//        Reservation result = reservationService.bookRoom(request);
//
//        assertNotNull(result);
//        assertEquals(1L, result.getId());
//    }
//}
