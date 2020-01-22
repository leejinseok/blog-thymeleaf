package com.example.thymeleaf.service;

import com.example.thymeleaf.domain.user.User;
import com.example.thymeleaf.domain.user.UserException;
import com.example.thymeleaf.domain.user.UserRepository;
import com.example.thymeleaf.dto.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public User saveUser(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();

        if (userRepository.findByUsername(username).size() > 0) {
            throw new UserException.AlreadyExistNickname(username);
        }

        if (userRepository.findByEmail(email).size() > 0) {
            throw new UserException.AlreadyExistEmail(username);
        }

        User user = User.create(email, username, bCryptPasswordEncoder.encode(password));
        return userRepository.save(user);
    }



    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
