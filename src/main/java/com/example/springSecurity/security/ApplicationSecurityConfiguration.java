package com.example.springSecurity.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

import static com.example.springSecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests((auth) -> auth
                        .antMatchers("/", "index", "/css/*").permitAll()
                        .antMatchers("/students/**").hasRole(STUDENT.name())
                        .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails paulStudent = User.builder()
                .username("paul")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();


        UserDetails peterAdmin = User.builder()
                .username("peter")
                .password(passwordEncoder.encode("password1234"))
                .roles(ADMIN.name())
                .build();


        UserDetails adamAdmin = User.builder()
                .username("adam")
                .password(passwordEncoder.encode("password1234"))
                .roles(ADMIN_TRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(paulStudent, peterAdmin, adamAdmin);


    }


}
