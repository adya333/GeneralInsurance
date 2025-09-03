package com.insurance.general_insurance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Creates a PasswordEncoder bean that can be used anywhere in the application.
     * We are using BCrypt, which is a strong, industry-standard hashing algorithm.
     * @return an instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain.
     * For now, we will disable CSRF and permit all requests to allow us to test our API.
     * We will configure this properly with authentication later.
     * @param http the HttpSecurity to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable Cross-Site Request Forgery protection for now
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll() // Allow all incoming requests
                );
        return http.build();
    }
}

