package com.aya.meetingapp.MeetingBooking.security;

import com.aya.meetingapp.MeetingBooking.service.ReservationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class ReservationSecurity {

    private final ReservationService reservationService;

    public ReservationSecurity(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Called by @PreAuthorize in controllers:
     * @param reservationId id of reservation being accessed
     * @param authentication current authentication
     * @return true if current user is the owner of the reservation
     */
    public boolean isOwner(Long reservationId, Authentication authentication) {
        Long ownerId = reservationService.getReservationOwnerId(reservationId);
        if (ownerId == null) return false;
        String currentUsername = authentication.getName();
        return reservationService.isUserOwner(currentUsername, ownerId);
    }
}
