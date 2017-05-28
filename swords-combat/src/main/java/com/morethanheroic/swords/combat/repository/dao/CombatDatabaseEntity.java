package com.morethanheroic.swords.combat.repository.dao;

import com.morethanheroic.swords.combat.domain.CombatType;
import lombok.Getter;

@Getter
public class CombatDatabaseEntity {

    private int id;
    private int userId;
    private int monsterId;
    private int monsterHealth;
    private int monsterMana;
    private CombatType type;
}
