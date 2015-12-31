package com.morethanheroic.swords.user.view.request;

import com.morethanheroic.swords.race.model.Race;
import com.morethanheroic.swords.validator.annotation.StringEnumeration;
import com.morethanheroic.swords.validator.annotation.UniqueInDatabaseTable;
import org.hibernate.validator.constraints.Email;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@GroupSequence({RegistrationRequest.class, RegistrationRequest.OrderFirst.class, RegistrationRequest.OrderSecond.class})
public class RegistrationRequest {

    //TODO: refactor UniqueInDb to its own common-validation module also rename it to UniqueInDatabase.
    @NotNull(message = "Email is required.")
    @Email(message = "Email must be a valid email.")
    @UniqueInDatabaseTable(field = "email", table = "users", message = "An user with this email already exists.", groups = OrderFirst.class)
    private String email;
    @NotNull(message = "Username is required.")
    @Size(min = 6, max = 16, message = "Username must be between 6 and 16 characters.")
    @UniqueInDatabaseTable(field = "username", table = "users", message = "An user with this username already exists.", groups = OrderSecond.class)
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

    public interface OrderFirst {
    }

    public interface OrderSecond {
    }
}