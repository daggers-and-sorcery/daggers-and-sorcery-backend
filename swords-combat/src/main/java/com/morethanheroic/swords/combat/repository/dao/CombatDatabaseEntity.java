package com.morethanheroic.swords.combat.repository.dao;

import lombok.Getter;

@Getter
public class CombatDatabaseEntity {

    private int id;
    private int userId;
    private int monsterId;
    private int monsterHealth;
    private int monsterMana;
}
