package com.morethanheroic.swords.combat.domain;

import com.morethanheroic.swords.combat.service.CombatMessageBuilder;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;

public class Combat {

    private final CombatMessageBuilder combatMessageBuilder;
    private final UserEntity userEntity;
    private final MonsterDefinition monsterDefinition;

    private CombatResult result = new CombatResult();

    private int turn = 0;
    private int userHealth;
    private int monsterHealth;

    public Combat(UserEntity userEntity, MonsterDefinition monsterDefinition, CombatMessageBuilder combatMessageBuilder) {
        this.userEntity = userEntity;
        this.monsterDefinition = monsterDefinition;
        this.combatMessageBuilder = combatMessageBuilder;

        this.userHealth = userEntity.getHealth();
        this.monsterHealth = monsterDefinition.getHealth();
    }

    public CombatResult doFight() {
        buildCombatInitiation();

        while(userHealth > 0 && monsterHealth > 0) {
            userHealth = 0;
        }

        return result;
    }

    private void takeTurn(CombatResult result) {

    }

    private void buildCombatInitiation() {
        result.addMessage(combatMessageBuilder.buildFightInitialisationMessage(monsterDefinition.getName()));
    }
}
