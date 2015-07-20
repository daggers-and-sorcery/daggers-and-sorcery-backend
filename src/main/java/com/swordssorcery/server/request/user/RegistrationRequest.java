package com.swordssorcery.server.request.user;

import com.swordssorcery.server.game.race.Race;
import com.swordssorcery.server.model.db.user.UserDatabaseEntity;
import com.swordssorcery.server.validator.annotation.StringEnumeration;
import com.swordssorcery.server.validator.annotation.UniqueInDb;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotNull(message = "Email is required.")
    @Email(message = "Email must be a valid email.")
    @UniqueInDb(field = "email", model = UserDatabaseEntity.class, message = "An user with this email already exists.")
    private String email;
    @NotNull(message = "Username is required.")
    @Size(min = 6, max = 16, message = "Username must be between 6 and 16 characters.")
    @UniqueInDb(field = "username", model = UserDatabaseEntity.class, message = "An user with this username already exists.")
    private String username;
    @NotNull(message = "Password is required.")
    @Size(min = 6, max = 16, message = "Password must be between 6 and 16 characters.")
    private String passwordFirst;
    @NotNull(message = "Second password is required.")
    @Size(min = 6, max = 16, message = "Second password must be between 6 and 16 characters.")
    private String passwordSecond;
    @StringEnumeration(enumClass = Race.class)
    private String race;

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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }
}
