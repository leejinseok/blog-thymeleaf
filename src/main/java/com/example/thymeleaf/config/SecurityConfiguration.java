package com.example.thymeleaf.config;

import com.example.thymeleaf.security.JwtAuthenticationFilter;
import com.example.thymeleaf.security.LoginFailureHandler;
import com.example.thymeleaf.security.LoginSuccessHandler;
import com.example.thymeleaf.security.UserDetailsAuthenticationProvider;
import com.example.thymeleaf.service.UserDetailsServiceImpl;
import com.example.thymeleaf.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailService;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
            .antMatchers("/static/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/login/**").permitAll()
            .antMatchers("/auth/register").permitAll()
            .antMatchers("/articles").authenticated()
            .and()
            .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/articles")
                .failureHandler(loginFailureHandler())
            .and()
            .addFilter(jwtAuthenticationFilter())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
    }

    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtUtil());
    }

    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new UserDetailsAuthenticationProvider(userDetailService, bCryptPasswordEncoder());
    }
}


