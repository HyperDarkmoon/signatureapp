package com.hyper.signatureapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())  // Disable CORS (should be managed in WebConfig)
                .csrf(csrf -> csrf.disable())  // Disable CSRF (enable it in production with proper configuration)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register").permitAll()  // Allow registration without authentication
                        .requestMatchers("/api/users/check").permitAll()  // Allow check user existence without authentication
                        .requestMatchers("/api/users/information").permitAll()
                        .anyRequest().authenticated()  // Require authentication for other requests
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Customize the login page URL if needed
                        .permitAll()  // Allow unauthenticated access to login page
                )
                .httpBasic(withDefaults());  // Enable HTTP Basic authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Bean for encoding passwords
    }
}
