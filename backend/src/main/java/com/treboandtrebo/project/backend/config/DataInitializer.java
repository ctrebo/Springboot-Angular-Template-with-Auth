package com.treboandtrebo.project.backend.config;

import com.treboandtrebo.project.backend.entity.ApplicationUser;
import com.treboandtrebo.project.backend.repository.ApplicationUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final ApplicationUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ApplicationUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void init() {
        if (userRepository.findUserByEmail("admin@email.com") == null) {
            ApplicationUser admin = ApplicationUser.builder()
                .firstName("Admin")
                .lastName("User")
                .email("admin@email.com")
                .password(passwordEncoder.encode("password"))
                .admin(true)
                .build();

            userRepository.save(admin);
        }

        if (userRepository.findUserByEmail("user@email.com") == null) {
            ApplicationUser user = ApplicationUser.builder()
                .firstName("Regular")
                .lastName("User")
                .email("user@email.com")
                .password(passwordEncoder.encode("password"))
                .admin(false)
                .build();

            userRepository.save(user);
        }

    }
}