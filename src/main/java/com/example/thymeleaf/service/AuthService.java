package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.user.User;
import com.example.thymeleaf.domain.user.UserRepository;
import com.example.thymeleaf.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public User saveUser(UserRequestDto userRequestDto) {
        User user = User.create(userRequestDto.getUsername(), bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        return userRepository.save(user);
    }
}
