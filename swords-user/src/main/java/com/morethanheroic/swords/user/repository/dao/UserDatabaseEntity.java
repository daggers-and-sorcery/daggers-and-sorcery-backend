package com.morethanheroic.swords.user.repository.dao;

import com.morethanheroic.swords.race.model.Race;

import java.time.Instant;

/**
 * Contains an user's data freshly fetched from the database.
 */
public class UserDatabaseEntity {

    private int id;
    private String email;
    private String username;
    private String password;
    private Race race;
    private Instant registrationDate;
    private Instant lastLoginDate;
    private Instant lastRegenerationDate;
    private int health;
    private int mana;
    private int movement;
    private int scavengingPoint;

    public UserDatabaseEntity() {
    }

    public UserDatabaseEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public String toString() {
        return "[id: " + getId() + " name: " + username + "]";
    }

    public Instant getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Instant registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Instant getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Instant lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Instant getLastRegenerationDate() {
        return lastRegenerationDate;
    }

    public void setLastRegenerationDate(Instant lastRegenerationDate) {
        this.lastRegenerationDate = lastRegenerationDate;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScaveningPoint() {
        return scavengingPoint;
    }

    public void setScavengingPoint(int scavengingPoint) {
        this.scavengingPoint = scavengingPoint;
    }
}
