package com.morethanheroic.swords.combat.domain.entity;

import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;

public class MonsterCombatEntity extends CombatEntity {

    private MonsterDefinition monsterDefinition;

    public MonsterCombatEntity(MonsterDefinition monsterDefinition) {
        this.monsterDefinition = monsterDefinition;

        this.setMaximumHealth(monsterDefinition.getHealth());
        this.setActualHealth(monsterDefinition.getHealth());

        //TODO: Define mana on monsters too
        this.setMaximumMana(0);
        this.setActualMana(0);
    }

    public MonsterDefinition getMonsterDefinition() {
        return monsterDefinition;
    }

    @Override
    public void terminate() {
        //atm nothing
    }
}
