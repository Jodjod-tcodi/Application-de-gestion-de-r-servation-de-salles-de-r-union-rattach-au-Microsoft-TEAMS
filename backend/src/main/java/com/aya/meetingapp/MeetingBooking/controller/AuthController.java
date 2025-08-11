package com.aya.meetingapp.MeetingBooking.controller;

import com.aya.meetingapp.MeetingBooking.dto.AuthenticationRequestDto;
import com.aya.meetingapp.MeetingBooking.dto.AuthenticationResponseDto;
import com.aya.meetingapp.MeetingBooking.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto request) {
        try {
            // authenticate user (this triggers password check via DaoAuthenticationProvider)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // load full UserDetails (authorities will be read from DB via AppUserDetailsService)
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

            // generate token including roles
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponseDto(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
