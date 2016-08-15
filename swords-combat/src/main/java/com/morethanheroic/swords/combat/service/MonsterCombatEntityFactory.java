package com.morethanheroic.swords.combat.service;

import org.springframework.stereotype.Service;

import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;

@Service
public class MonsterCombatEntityFactory {

    public MonsterCombatEntity newMonsterCombatEntity(final MonsterDefinition monsterDefinition) {
        return new MonsterCombatEntity(monsterDefinition);
    }

    public MonsterCombatEntity newMonsterCombatEntity(final MonsterDefinition monsterDefinition, final int actualHealth, final int actualMana) {
        return new MonsterCombatEntity(monsterDefinition, actualHealth, actualMana);
    }
}
