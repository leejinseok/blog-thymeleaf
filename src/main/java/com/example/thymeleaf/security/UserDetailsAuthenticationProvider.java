package com.example.thymeleaf.security;

import com.example.thymeleaf.domain.user.AuthException;
import com.example.thymeleaf.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserDetailsServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        if (!passwordEncoder.matches(userDetails.getPassword(), usernamePasswordAuthenticationToken.getCredentials().toString())) {
            throw new AuthException.PasswordNotMatchException();
        }

    }

    @Override
    protected UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return userService.loadUserByUsername(email);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return super.authenticate(authentication);
    }

}
