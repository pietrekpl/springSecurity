package com.example.springSecurity.auth;


import com.example.springSecurity.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fake")
@RequiredArgsConstructor
public class ApplicationUserDAOService implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
          new ApplicationUser(ApplicationUserRole.ADMIN.getGrantedAuthorities(),passwordEncoder.encode("password"),"peter",true,true,true,true),
          new ApplicationUser(ApplicationUserRole.STUDENT.getGrantedAuthorities(),passwordEncoder.encode("password"),"paul",true,true,true,true),
          new ApplicationUser(ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),passwordEncoder.encode("password"),"adam",true,true,true,true)
        );

        return applicationUsers;
    }



}
