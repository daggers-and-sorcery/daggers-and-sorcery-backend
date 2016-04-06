package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.attribute.service.calc.GlobalAttributeCalculator;
import com.morethanheroic.swords.combat.domain.entity.MonsterCombatEntity;
import com.morethanheroic.swords.combat.domain.entity.UserCombatEntity;
import com.morethanheroic.swords.monster.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class Combat {

    private final UserCombatEntity userCombatEntity;
    private final MonsterCombatEntity monsterCombatEntity;

    private int turn = 0;

    public Combat(UserEntity userEntity, MonsterDefinition monsterDefinition, GlobalAttributeCalculator globalAttributeCalculator) {
        this.userCombatEntity = new UserCombatEntity(userEntity, globalAttributeCalculator);
        this.monsterCombatEntity = new MonsterCombatEntity(monsterDefinition);
    }

    public int getTurn() {
        return turn;
    }

    public void increaseTurn() {
        this.turn++;
    }

    public UserCombatEntity getUserCombatEntity() {
        return userCombatEntity;
    }

    public MonsterCombatEntity getMonsterCombatEntity() {
        return monsterCombatEntity;
    }
}
