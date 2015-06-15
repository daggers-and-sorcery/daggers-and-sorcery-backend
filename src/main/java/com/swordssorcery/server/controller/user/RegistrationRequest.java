package com.swordssorcery.server.controller.user;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;

public class RegistrationRequest {

    @NotNull(message = "Email is required.")
    @Email(message = "Email must be a valid email.")
    private String email;
    @NotNull(message = "Username is required.")
    @Size(min = 6, max = 16, message="Username must be between 6 and 16 characters.")
    private String username;
    @NotNull(message = "Password is required.")
    @Size(min = 6, max = 16, message="Password must be between 6 and 16 characters.")
    private String passwordFirst;
    @NotNull(message = "Second password is required.")
    @Size(min = 6, max = 16, message="Second password must be between 6 and 16 characters.")
    private String passwordSecond;
    @Max(value = 7, message = "Invalid race id.")
    @Min(value = 0, message = "Invalid race id.")
    private int race;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordFirst() {
        return passwordFirst;
    }

    public void setPasswordFirst(String passwordFirst) {
        this.passwordFirst = passwordFirst;
    }

    public String getPasswordSecond() {
        return passwordSecond;
    }

    public void setPasswordSecond(String passwordSecond) {
        this.passwordSecond = passwordSecond;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }
}
