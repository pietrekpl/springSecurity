package com.example.springSecurity.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.net.http.HttpRequest;

import static com.example.springSecurity.security.ApplicationUserPermission.COURSE_WRITE;
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
                        .antMatchers(HttpMethod.DELETE, "/management/students/**").hasAuthority(COURSE_WRITE.getPermision())
                        .antMatchers(HttpMethod.PUT, "/management/students/**").hasAuthority(COURSE_WRITE.getPermision())
                        .antMatchers(HttpMethod.POST, "/management/students/**").hasAuthority(COURSE_WRITE.getPermision())
                        .antMatchers(HttpMethod.GET, "/management/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                        .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails paulStudent = User.builder()
                .username("paul")
                .password(passwordEncoder.encode("password"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();


        UserDetails peterAdmin = User.builder()
                .username("peter")
                .password(passwordEncoder.encode("password1234"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();


        UserDetails adamAdmin = User.builder()
                .username("adam")
                .password(passwordEncoder.encode("password1234"))
                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(paulStudent, peterAdmin, adamAdmin);


    }


}
