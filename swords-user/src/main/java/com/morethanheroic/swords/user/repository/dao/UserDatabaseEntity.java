package com.morethanheroic.swords.user.repository.dao;

import com.morethanheroic.swords.race.model.Race;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Contains an user's data freshly fetched from the database.
 */
public class UserDatabaseEntity {

    private int id;
    private String email;
    private String username;
    private String password;
    private Race race;
    private LocalDate registrationDate;
    private LocalDate lastLoginDate;
    //TODO: Rename lastRegenerationDate to lastRegenerationTime.
    private LocalTime lastRegenerationDate;
    private int map;
    private int x;
    private int y;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDate lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public LocalTime getLastRegenerationDate() {
        return lastRegenerationDate;
    }

    public void setLastRegenerationDate(LocalTime lastRegenerationDate) {
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
