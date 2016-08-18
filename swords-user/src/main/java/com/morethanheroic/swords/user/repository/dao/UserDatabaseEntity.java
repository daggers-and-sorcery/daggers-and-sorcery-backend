package com.morethanheroic.swords.user.repository.dao;

import com.morethanheroic.swords.race.model.Race;
import lombok.Data;

import java.time.Instant;

/**
 * Contains an user's data freshly fetched from the database.
 */
@Data
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
    private int explorationEvent;
    private int explorationState;

    public UserDatabaseEntity() {
    }

    public UserDatabaseEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
