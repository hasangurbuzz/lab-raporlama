package com.hasan.laboratuvarraporlama.security.auth;

import org.springframework.stereotype.Component;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> selectAppUserByUsername(String username);
}
