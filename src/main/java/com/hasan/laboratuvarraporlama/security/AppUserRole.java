package com.hasan.laboratuvarraporlama.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.hasan.laboratuvarraporlama.security.AppUserPermission.*;

public enum AppUserRole {

    USER(Sets.newHashSet(REPORT_READ, REPORT_WRITE)),
    ADMIN(Sets.newHashSet(REPORT_READ, REPORT_WRITE, REPORT_DELETE));

    private final Set<AppUserPermission> permissions;


    AppUserRole(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
