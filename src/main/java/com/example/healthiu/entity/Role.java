package com.example.healthiu.entity;

public enum Role {
    USER ("USER"),
    DOCTOR ("DOCTOR"),
    ADMIN ("ADMIN")
    ;
    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
