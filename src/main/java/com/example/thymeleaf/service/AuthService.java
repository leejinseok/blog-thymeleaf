package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.user.User;
import com.example.thymeleaf.domain.user.UserException;
import com.example.thymeleaf.domain.user.UserRepository;
import com.example.thymeleaf.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public User saveUser(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        String name = userRequestDto.getName();

        if (userRepository.findByEmail(email).size() > 0) {
            throw new UserException.AlreadyExistEmail(email);
        }

        User user = User.create(email, name, bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
