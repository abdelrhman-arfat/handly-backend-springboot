package com.project.handly.Configuration;

<<<<<<< HEAD
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.handly.DTOs.User.OauthDTO;
import com.project.handly.Utils.ResponseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
=======
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
<<<<<<< HEAD
import org.springframework.security.oauth2.core.user.OAuth2User;
=======
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)          // Disable CSRF for APIs
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                )
<<<<<<< HEAD
                .httpBasic(Customizer.withDefaults())
                .oauth2Login(
                        oauth2-> oauth2
                        .defaultSuccessUrl("/login/oauth2/google/callback", true)
                        .failureUrl("/oauth2/failure"));
=======
                .httpBasic(Customizer.withDefaults());
>>>>>>> 19f644cf08a16ec006c41ec5432fae67c3da07fb

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}