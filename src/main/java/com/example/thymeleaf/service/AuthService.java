package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.user.User;
import com.example.thymeleaf.domain.user.UserException;
import com.example.thymeleaf.domain.user.UserRepository;
import com.example.thymeleaf.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public User saveUser(UserRequestDto userRequestDto) {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        if (userRepository.findByUsername(username).size() > 0) {
            throw new UserException.AlreadyExist(username);
        }

        User user = User.create(username, bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }
}
