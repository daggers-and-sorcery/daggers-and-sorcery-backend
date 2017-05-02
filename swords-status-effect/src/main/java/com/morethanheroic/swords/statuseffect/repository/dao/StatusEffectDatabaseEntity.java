package com.morethanheroic.swords.statuseffect.repository.dao;

import lombok.Data;

import java.time.Instant;

/**
 * Contains the status effect data from the database.
 */
@Data
public class StatusEffectDatabaseEntity {

    private int id;
    private int userId;
    private int statusEffectId;
    private Instant expirationTime;
}
