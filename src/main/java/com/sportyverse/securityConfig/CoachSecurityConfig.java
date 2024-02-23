package com.sportyverse.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CoachSecurityConfig {

    @Bean
    public PasswordEncoder coachPasswordEncoder() { return new BCryptPasswordEncoder(); }
}
