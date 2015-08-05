package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.Combat;
import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private CombatMessageBuilder combatMessageBuilder;

    @Autowired
    public CombatManager(CombatMessageBuilder combatMessageBuilder) {
        this.combatMessageBuilder = combatMessageBuilder;
    }

    public CombatResult initiateCombat(UserEntity user, MonsterDefinition monster) {
        Combat combat = new Combat(user, monster, combatMessageBuilder);

        return combat.doFight();
    }
}
