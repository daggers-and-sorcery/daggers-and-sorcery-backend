package com.morethanheroic.swords.combat.service;

import com.morethanheroic.swords.combat.domain.CombatResult;
import com.morethanheroic.swords.monster.service.domain.MonsterDefinition;
import com.morethanheroic.swords.user.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CombatManager {

    private final CombatCalculator combatCalculator;

    @Autowired
    public CombatManager(CombatCalculator combatCalculator) {
        this.combatCalculator = combatCalculator;
    }

    public CombatResult initiateCombat(UserEntity user, MonsterDefinition monster) {
        return combatCalculator.doFight(user, monster);
    }
}
