package com.aya.meetingapp.MeetingBooking.repository;

import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
