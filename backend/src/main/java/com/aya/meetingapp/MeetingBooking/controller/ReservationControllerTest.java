//package com.aya.meetingapp.MeetingBooking.controller;
//
//import com.aya.meetingapp.MeetingBooking.dto.ReservationRequestDto;
//import com.aya.meetingapp.MeetingBooking.entity.Reservation;
//import com.aya.meetingapp.MeetingBooking.service.ReservationService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static reactor.core.publisher.Mono.when;
//
//class ReservationControllerTest {
//
//    @InjectMocks
//    private ReservationController reservationController;
//
//    @Mock
//    private ReservationService reservationService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testBookRoom() {
//        ReservationRequestDto request = new ReservationRequestDto(1L, 1L,
//                LocalDateTime.now().plusMinutes(30), LocalDateTime.now().plusHours(2));
//
//        Reservation expected = new Reservation();
//        expected.setId(1L);
//
//        when(reservationService.bookRoom(request)).thenReturn(expected);
//
//        ResponseEntity<Reservation> response = reservationController.bookRoom(request);
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(1L, response.getBody().getId());
//    }
//}
