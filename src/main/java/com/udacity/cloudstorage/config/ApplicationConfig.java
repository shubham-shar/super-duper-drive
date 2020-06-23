package com.udacity.cloudstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Configuration
public class ApplicationConfig {
    @Bean
    public PasswordEncoder configurePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
