package com.aya.meetingapp.MeetingBooking.config;


import com.aya.meetingapp.MeetingBooking.entity.AppUser;
import com.aya.meetingapp.MeetingBooking.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {  // only insert if DB empty
                AppUser aya = new AppUser("aya", "ayahachana2023@gmail.com", passwordEncoder.encode("mypassword"), "Admin");
                AppUser yasmine = new AppUser("yasmine", "yasminejedidi03@gmail.com", passwordEncoder.encode("herpassword"), "Admin");
                AppUser louay = new AppUser("louay", "louay.zeidi@medtech.com", passwordEncoder.encode("hispassword"), "Admin");
                userRepository.saveAll(List.of(aya, yasmine, louay));
            }
        };
    }}
