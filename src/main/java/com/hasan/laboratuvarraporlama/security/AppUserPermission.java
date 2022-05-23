package com.hasan.laboratuvarraporlama.security;

public enum AppUserPermission {

    REPORT_READ("report:read"),
    REPORT_WRITE("report:write"),
    REPORT_DELETE("report:delete");
    

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
