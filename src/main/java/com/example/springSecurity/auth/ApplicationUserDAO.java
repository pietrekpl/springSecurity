package com.example.springSecurity.auth;

import java.util.Optional;

public interface ApplicationUserDAO {

    Optional<ApplicationUser>selectApplicationUserByUsername(String username);

}
