package com.aya.meetingapp.MeetingBooking.service;

import com.aya.meetingapp.MeetingBooking.dto.UserDto;
import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.mapper.UserMapper;
import com.aya.meetingapp.MeetingBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper; //well see why later

    @Autowired
    public UserService(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        AppUser user = userRepo.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }
    // inside UserService class, add:
    public UserDto getUserByUsername(String username) {
        AppUser user = userRepo.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return userMapper.toDto(user);
    }

}
