package com.hasan.laboratuvarraporlama.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hasan.laboratuvarraporlama.security.AppUserRole.ADMIN;
import static com.hasan.laboratuvarraporlama.security.AppUserRole.USER;

@Repository("fake")
public class AppUserDaoService implements AppUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return  getAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<AppUser> getAppUsers() {
        List<AppUser> appUsers = Lists.newArrayList(
                new AppUser(
                        "admin",
                        passwordEncoder.encode("admin"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new AppUser(
                        "user",
                        passwordEncoder.encode("password"),
                        USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return appUsers;
    }
}
