package com.aya.meetingapp.MeetingBooking.mapper;

import com.aya.meetingapp.MeetingBooking.dto.UserDto;
import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(AppUser user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }

    public AppUser toEntity(UserDto dto) {
        AppUser user = new AppUser();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        return user;
    }
}
