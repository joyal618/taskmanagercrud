package com.dexlock.javacrud.models;

import java.security.Principal;

public class User implements Principal {

    private String name;
    private String email;
    public enum role {SuperAdmin, ProjectManager,User}
    private role userRole;
    private String password;

    public User() {
    }

    public User(String name, String email, role userRole, String password) {
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.password = password;
    }

    public role getUserRole() {
        return userRole;
    }

    public void setUserRole(role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
