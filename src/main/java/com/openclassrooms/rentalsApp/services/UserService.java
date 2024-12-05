package com.openclassrooms.rentalsApp.services;

import com.openclassrooms.rentalsApp.dtos.RegisterRequest;
import com.openclassrooms.rentalsApp.dtos.UserDto;
import com.openclassrooms.rentalsApp.models.User;
import com.openclassrooms.rentalsApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User saveUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return null;
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated_at(LocalDate.now());
        user.setUpdated_at(LocalDate.now());
        return userRepository.save(user);
    }

    public UserDto getUserByEmail(String email) {
        // convert User to UserDto
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }

        // Convertir l'entité User en DTO UserDto
        return modelMapper.map(user, UserDto.class);
    }


    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        return modelMapper.map(user, UserDto.class);
    }
}
